package com.study.companytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompanyTrackerApplication{
    public static void main(String[] args) {
        SpringApplication.run(CompanyTrackerApplication.class, args);
    }}