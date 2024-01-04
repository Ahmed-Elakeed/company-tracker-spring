package com.study.companytracker.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginDTO {

    private String email;
    private String password;
}
