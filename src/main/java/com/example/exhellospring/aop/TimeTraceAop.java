package com.example.exhellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* com.example.exhellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START = " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long diff = end - start;

            System.out.println("END = " + joinPoint.toString() + " " + diff + "ms");
        }
    }

}
