package com.study.companytracker.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchJobRunner implements CommandLineRunner {

    private final Job job;
    private final JobLauncher jobLauncher;

    public BatchJobRunner(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void run(String... args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("JobID", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//
//        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//
//        System.out.println("Batch Job Status: " + jobExecution.getStatus());

    }
}
