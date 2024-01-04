package com.study.companytracker.converter;

import com.study.companytracker.dto.TaskDTO;
import com.study.companytracker.model.Employee;
import com.study.companytracker.model.Project;
import com.study.companytracker.model.Task;

public class TaskConverter {

    private TaskConverter(){}

    public static TaskDTO toDto(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .status(task.getStatus())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .name(task.getName())
                .description(task.getDescription())
                .employeeName(task.getEmployee().getFullName())
                .employeeId(task.getEmployee().getId())
                .projectName(task.getProject().getName())
                .projectId(task.getProject().getId())
                .build();
    }

    public static Task toEntity(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .status(taskDTO.getStatus())
                .startDate(taskDTO.getStartDate())
                .endDate(taskDTO.getEndDate())
                .name(taskDTO.getName())
                .description(taskDTO.getDescription())
                .employee(Employee.builder()
                        .id(taskDTO.getEmployeeId())
                        .build())
                .project(Project.builder()
                        .id(taskDTO.getProjectId())
                        .build())
                .build();
    }
}
