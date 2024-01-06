package com.study.companytracker;

import com.study.companytracker.service.TaskReportService;
import com.study.companytracker.util.CompanyTrackerLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyTrackerApplication implements CommandLineRunner {

    @Autowired
    private TaskReportService taskReportService;
    public static void main(String[] args) {
        SpringApplication.run(CompanyTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        this.taskReportService.csvTasksReportSchedule();
        CompanyTrackerLogger.LOGGER().info("Company Tracker Application Started");
    }
}
