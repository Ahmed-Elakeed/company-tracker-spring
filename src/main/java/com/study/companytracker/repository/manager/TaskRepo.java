package com.study.companytracker.repository.manager;

import com.study.companytracker.dto.TaskReportDTO;

import java.util.List;

public interface TaskRepo {
    List<TaskReportDTO> fetchTasksReportData();
}
