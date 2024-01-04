package com.study.companytracker.converter;

import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.model.Department;

public class DepartmentConverter {

    private DepartmentConverter(){}

    public static DepartmentDTO toDto(Department department){
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static Department toEntity(DepartmentDTO departmentDTO){
        return Department.builder()
                .id(departmentDTO.getId())
                .name(departmentDTO.getName())
                .build();
    }
}
