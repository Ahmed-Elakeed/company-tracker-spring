package com.study.companytracker.dto;


import com.study.companytracker.util.enums.AdminRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminLoginResponseDTO {
    private Long id;
    private String token;
    private String email;
    private AdminRole role;
}
