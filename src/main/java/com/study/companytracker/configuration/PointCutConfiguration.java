package com.study.companytracker.configuration;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutConfiguration {

    @Pointcut(value = "execution(* com.study.companytracker.service.*.*(..))")
    public void servicePackagePointCut(){}

    @Pointcut(value = "execution(* com.study.companytracker.exception.RestExceptionHandler.*(..))")
    public void exceptionHandlerPointCut(){}
}
