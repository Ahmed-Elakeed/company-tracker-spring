package com.study.companytracker.dto;

import com.study.companytracker.util.enums.ProjectStatus;
import com.study.companytracker.util.enums.TaskStatus;
import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskReportDTO {
    private String departmentName;
    private String projectName;
    private ProjectStatus projectStatus;
    private String employeeName;
    private String taskName;
    private Date taskStartDate;
    private Date taskEndDate;
    private TaskStatus taskStatus;
}
