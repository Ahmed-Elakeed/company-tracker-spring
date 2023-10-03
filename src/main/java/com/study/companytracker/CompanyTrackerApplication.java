package com.study.companytracker;

import com.study.companytracker.util.CompanyTrackerLogger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyTrackerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CompanyTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CompanyTrackerLogger.LOGGER().info("Company Tracker Application Started");
    }
}

/*

TODO List
- Make all rest exception responses return from rest exception handlers
- Add more details to mails
- Add specified error codes and error messages and use them as exception messages in all service classes
- Add all needed custom exception and listen to to them of thrown from any service class by AOP advice

 */
