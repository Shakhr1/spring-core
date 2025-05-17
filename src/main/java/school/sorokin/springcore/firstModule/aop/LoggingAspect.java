package school.sorokin.springcore.firstModule.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* school.sorokin.springcore.firstModule.TaskManager.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Перед вызовом метода: " + joinPoint.getSignature().getName());
    }
}
