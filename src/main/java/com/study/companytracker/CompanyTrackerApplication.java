package com.study.companytracker;

import com.study.companytracker.util.CompanyTrackerLogger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompanyTrackerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CompanyTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CompanyTrackerLogger.LOGGER().info("Company Tracker Application Started");
    }
}
