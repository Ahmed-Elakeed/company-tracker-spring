package com.study.companytracker.service;

import com.study.companytracker.dto.MailAttachmentDTO;
import com.study.companytracker.exception.MailAttachmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String text, List<MailAttachmentDTO> attachmentDTOList) {
        MimeMessage message = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("dev@company.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            if (attachmentDTOList != null && !attachmentDTOList.isEmpty()) {
                try {
                    for (MailAttachmentDTO attachmentDTO : attachmentDTOList) {
                        helper.addAttachment(
                                attachmentDTO.getAttachmentName(),
                                attachmentDTO.getAttachmentResource()
                        );
                    }
                } catch (Exception exception) {
                    throw new MailAttachmentException("Failed to add all attachments to mail");
                }
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
