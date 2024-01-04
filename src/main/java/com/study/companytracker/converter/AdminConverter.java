package com.study.companytracker.converter;

import com.study.companytracker.dto.AdminDTO;
import com.study.companytracker.model.Admin;

public class AdminConverter {
    private AdminConverter(){}

    public static AdminDTO toDto(Admin admin){
        return AdminDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .build();
    }

    public static Admin toEntity(AdminDTO adminDTO){
        return Admin.builder()
                .id(adminDTO.getId())
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(adminDTO.getPassword())
                .role(adminDTO.getRole())
                .build();
    }
}
