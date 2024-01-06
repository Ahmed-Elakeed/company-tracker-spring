package com.study.companytracker.aop;

import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.MailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
@RequiredArgsConstructor
public class ExceptionNotificationMailJob {

    @Value("#{'${receivers.mails}'.split(',')}")
    private List<String> receivers;

    private final MailService mailService;


    @AfterReturning(value = "com.study.companytracker.configuration.PointCutConfiguration.exceptionHandlerPointCut()", returning = "genericRestResponse")
    public void customResponseExceptionMailHandler(GenericRestResponse<?> genericRestResponse) {
        this.receivers.forEach(mail -> this.mailService.sendMail(
                        mail,
                        "Exception Notification",
                        genericRestResponse.getErrorMessage(),
                        null
                )
        );
    }

}
