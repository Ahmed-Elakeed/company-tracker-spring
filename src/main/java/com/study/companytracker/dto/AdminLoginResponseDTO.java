package com.study.companytracker.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminLoginResponseDTO {
    private Long id;
    private String token;
    private String name;
    private String email;
    private Integer role;
}
