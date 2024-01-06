package com.study.companytracker.util;

import com.study.companytracker.dto.TaskReportDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CSVUtil {

    private static final String CSV_FILE = "tasks-report.csv";

    private CSVUtil() {
    }

    public static String generateTasksReportCSVFile(List<TaskReportDTO> taskReportDTOList) {
        try (
                FileWriter writer = new FileWriter(CSV_FILE);

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
                        formatDateToString(taskReportDTO.getTaskStartDate()),
                        formatDateToString(taskReportDTO.getTaskEndDate()),
                        taskReportDTO.getTaskStatus()
                );
            }
            csvPrinter.flush();
            return CSV_FILE;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateToString(Date date) {
        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy");

        // Format the date using SimpleDateFormat and return the formatted string
        return sdf.format(date);
    }
}
