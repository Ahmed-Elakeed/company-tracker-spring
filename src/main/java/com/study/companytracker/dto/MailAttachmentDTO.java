package com.study.companytracker.dto;

import lombok.*;
import org.springframework.core.io.ByteArrayResource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MailAttachmentDTO {
    private String attachmentName;
    private ByteArrayResource attachmentResource;
}
