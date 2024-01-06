package com.study.companytracker.repository.manager;

import com.study.companytracker.dto.TaskReportDTO;
import com.study.companytracker.util.enums.TaskStatus;

import java.util.List;

public interface TaskRepo {
    List<TaskReportDTO> fetchTasksReportData(TaskStatus taskStatus);
}
