package com.study.companytracker.converter;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.model.Employee;

public class EmployeeConverter {

    public static EmployeeDTO toDto(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
    }

    public static Employee toEntity(EmployeeDTO employeeDTO){
        return Employee.builder()
                .id(employeeDTO.getId())
                .fullName(employeeDTO.getFullName())
                .email(employeeDTO.getEmail())
                .department(employeeDTO.getDepartment())
                .build();
    }
}
