package com.study.companytracker.converter;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.model.Department;
import com.study.companytracker.model.Employee;

public class EmployeeConverter {

    private EmployeeConverter(){}

    public static EmployeeDTO toDto(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getName())
                .build();
    }

    public static Employee toEntity(EmployeeDTO employeeDTO){
        return Employee.builder()
                .id(employeeDTO.getId())
                .fullName(employeeDTO.getFullName())
                .email(employeeDTO.getEmail())
                .department(Department.builder()
                        .id(employeeDTO.getDepartmentId())
                        .build())
                .build();
    }


}
