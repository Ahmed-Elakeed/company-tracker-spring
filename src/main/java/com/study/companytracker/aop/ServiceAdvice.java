package com.study.companytracker.aop;

import com.study.companytracker.util.CompanyTrackerLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceAdvice {

    @Before(value = "com.study.companytracker.configuration.PointCutConfiguration.servicePackagePointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        CompanyTrackerLogger.LOGGER().info("{} Method is about to executed",joinPoint.getSignature().getName());
    }

    @After(value = "com.study.companytracker.configuration.PointCutConfiguration.servicePackagePointCut()")
    public void afterAdvice(JoinPoint joinPoint){
        CompanyTrackerLogger.LOGGER().info("{} Method is executed",joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "com.study.companytracker.configuration.PointCutConfiguration.servicePackagePointCut()",throwing = "error")
    public void afterThrowingAdvice(JoinPoint joinPoint,Throwable error){
        CompanyTrackerLogger.LOGGER().error("Exception occurred while processing {} - Error details : {}",
                joinPoint.getSignature().getName(),
                error.getMessage());
    }

    @Around(value = "com.study.companytracker.configuration.PointCutConfiguration.servicePackagePointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        CompanyTrackerLogger.LOGGER().info("Around {} method",proceedingJoinPoint.getSignature().getName());
        Object[] args = proceedingJoinPoint.getArgs();
        if(args.length>0){
            System.out.print("Arguments passed: " );
            for (int i = 0; i < args.length; i++) {
                System.out.print("arg "+(i+1)+": "+args[i]);
            }
        }
        Object result = proceedingJoinPoint.proceed(args);
        CompanyTrackerLogger.LOGGER().info("Method {} returning {}",proceedingJoinPoint.getSignature().getName(),result);
        return result;
    }
}
