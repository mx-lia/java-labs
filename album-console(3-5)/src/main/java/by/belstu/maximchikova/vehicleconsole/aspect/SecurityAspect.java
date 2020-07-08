package by.belstu.maximchikova.vehicleconsole.aspect;

import by.belstu.maximchikova.vehicleconsole.exception.NotAuthenticatedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class SecurityAspect {
    @Pointcut("execution(public * by.belstu.maximchikova.vehicleconsole.service.VehicleService.*(..))")
    public void albumServiceMethod() {
    }

    @Around("albumServiceMethod()")
    public Object beforeAlbumServiceMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info(new Date() + " {} authenticated access by {}", joinPoint.toString(), authentication.getName());
            return joinPoint.proceed();
        } else {
            throw new NotAuthenticatedException(new Date() + " Not authenticated access: " + joinPoint.toString());
        }
    }
}
