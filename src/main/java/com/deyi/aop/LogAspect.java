package com.deyi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private void before() {
        System.out.println("=============before=============");
    }

    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String args= Arrays.toString(joinPoint.getArgs());
        System.out.println("=============begin invoke method=============");
        System.out.println("methodName="+methodName);
        System.out.println("args="+args);
        Object retVal=joinPoint.proceed();

        return  retVal;
    }

    private void afterReturning(JoinPoint point, Object returnValue){
        System.out.println("返回结果:"+returnValue);
    }

    private void afterThrowing(){
        System.out.println("=============afterThrowing=============");
    }

    private void finalMethod(){
        System.out.println("=============finished=============");
    }


}