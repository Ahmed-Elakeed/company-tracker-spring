package com.study.companytracker.dto;

import com.study.companytracker.util.enums.AdminRole;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminDTO {
    private String email;
    private String password;
    private AdminRole role;
}
