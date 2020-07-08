package by.belstu.maximchikova.vehicles.aspect;

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
    @Pointcut("execution(public * by.belstu.maximchikova.vehicles.controller.api.v1.VehicleControllerV1.*(..))")
    public void vehicleControllerEndpoint() {
    }

    @Before("vehicleControllerEndpoint()")
    public void beforeVehicleEndpointHit(JoinPoint joinPoint) {
        String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info(new Date() + "before {} args = [{}]", joinPoint.toString(), args);
    }

    @After("vehicleControllerEndpoint()")
    public void afterEndpointHit(JoinPoint joinPoint) {
        log.info(new Date() + "{} has been completed", joinPoint.toString());
    }

    @AfterThrowing(pointcut = "vehicleControllerEndpoint()", throwing = "exception")
    public void afterThrowingFromEndpoint(JoinPoint joinPoint, Throwable exception) {
        log.error(new Date() + "{} thrown exception {}", joinPoint.toString(), exception.getMessage());
    }
}
