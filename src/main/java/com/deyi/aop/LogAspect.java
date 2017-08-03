package com.deyi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private void before() {
        logger.info("=============before=============");
    }

    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String args= Arrays.toString(joinPoint.getArgs());
        logger.info("=============begin invoke method=============");
        logger.info("methodName="+methodName);
        logger.info("args="+args);
        Object retVal=joinPoint.proceed();

        return  retVal;
    }

    private void afterReturning(JoinPoint point, Object returnValue){
        logger.info("返回结果:"+returnValue);
    }

    private void afterThrowing(){
        logger.info("=============afterThrowing=============");
    }

    private void finalMethod(){
        logger.info("=============finished=============");
    }


}