package com.study.companytracker.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeDTO {

    private Long id;
    private String fullName;

    private String email;

    private Long departmentId;

    private String departmentName;
}
