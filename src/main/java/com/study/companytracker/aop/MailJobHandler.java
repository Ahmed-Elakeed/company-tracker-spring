package com.study.companytracker.aop;

import com.study.companytracker.dto.GenericRestResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@Aspect
@Configuration
@RequiredArgsConstructor
public class MailJobHandler {

    @Value("#{'${receivers.mails}'.split(',')}")
    private List<String> mails;

    private final JavaMailSender mailSender;


//    @AfterReturning(value = "com.study.companytracker.configuration.PointCutConfiguration.exceptionHandlerPointCut()",returning = "genericRestResponse")
//    public void exceptionMailHandler(GenericRestResponse<?> genericRestResponse){
//        System.out.println(this.mails);
//        this.mails.forEach(mail -> this.sendMail(mail,genericRestResponse));
//    }

    private void sendMail(String to, GenericRestResponse<?> genericRestResponse) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dev@company.com");
        message.setTo(to);
        message.setSubject(genericRestResponse.getResponseMessage().toString());
        message.setText(genericRestResponse.getErrorMessage());
        mailSender.send(message);
    }

}
