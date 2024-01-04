package com.study.companytracker.converter;

import com.study.companytracker.dto.ProjectDTO;
import com.study.companytracker.model.Department;
import com.study.companytracker.model.Project;

public class ProjectConverter {

    private ProjectConverter() {
    }

    public static ProjectDTO toDto(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .description(project.getDescription())
                .status(project.getStatus())
                .departmentId(project.getDepartment().getId())
                .departmentName(project.getDepartment().getName())
                .build();
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        return Project.builder()
                .id(projectDTO.getId())
                .name(projectDTO.getName())
                .startDate(projectDTO.getStartDate())
                .endDate(projectDTO.getEndDate())
                .description(projectDTO.getDescription())
                .status(projectDTO.getStatus())
                .department(Department.builder()
                        .id(projectDTO.getId())
                        .build())
                .build();
    }
}
