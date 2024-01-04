package com.study.companytracker.dto;

import com.study.companytracker.util.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String description;
    private ProjectStatus status;
    private Long departmentId;
    private String departmentName;
}
