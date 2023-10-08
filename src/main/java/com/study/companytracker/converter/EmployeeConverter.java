package com.study.companytracker.converter;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.model.Employee;

public class EmployeeConverter {

    public static EmployeeDTO convertEmployeeToEmployeeDto(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .build();
    }

}
