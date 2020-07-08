package by.belstu.maximchikova.vehicleconsole.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * by.belstu.maximchikova.vehicleconsole.controller.VehicleController.*(..))")
    public void albumControllerEndpoint() {
    }

    @Before("albumControllerEndpoint()")
    public void beforeAlbumEndpointHit(JoinPoint joinPoint) {
        String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info(new Date() + " before {} args = [{}]", joinPoint.toString(), args);
    }

    @After("albumControllerEndpoint()")
    public void afterEndpointHit(JoinPoint joinPoint) {
        log.info(new Date() + " {} has been completed", joinPoint.toString());
    }

    @AfterThrowing(pointcut = "albumControllerEndpoint()", throwing = "exception")
    public void afterThrowingFromEndpoint(JoinPoint joinPoint, Throwable exception) {
        log.error(new Date() + " {} thrown exception {}", joinPoint.toString(), exception.getMessage());
    }
}
