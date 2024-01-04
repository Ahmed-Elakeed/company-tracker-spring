package com.study.companytracker.dto;

import com.study.companytracker.util.enums.TaskStatus;
import lombok.*;


import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private TaskStatus status;
    private Long projectId;
    private String projectName;
    private String employeeName;
    private Long employeeId;
}
