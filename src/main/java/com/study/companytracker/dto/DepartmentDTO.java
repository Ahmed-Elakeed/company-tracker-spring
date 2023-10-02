package com.study.companytracker.dto;

import com.study.companytracker.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<Employee> employees;
}
