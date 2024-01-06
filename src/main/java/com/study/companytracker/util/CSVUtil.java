package com.study.companytracker.util;

import com.study.companytracker.dto.TaskReportDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUtil {

    private static final String CSV_FILE = "tasks-report.csv";

    private CSVUtil() {
    }

    public static CSVPrinter generateTasksReportCSVFile(List<TaskReportDTO> taskReportDTOList) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(
                                "Department name",
                                "Project name",
                                "Project status",
                                "Employee name",
                                "Task name",
                                "Task start date",
                                "Task end date",
                                "Task status"
                        ))
        ) {
            for (TaskReportDTO taskReportDTO : taskReportDTOList) {
                csvPrinter.printRecord(
                        taskReportDTO.getDepartmentName(),
                        taskReportDTO.getProjectName(),
                        taskReportDTO.getProjectStatus(),
                        taskReportDTO.getEmployeeName(),
                        taskReportDTO.getTaskName(),
                        taskReportDTO.getTaskStartDate(),
                        taskReportDTO.getTaskEndDate(),
                        taskReportDTO.getTaskStatus()
                );
            }
            csvPrinter.flush();
            return csvPrinter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
