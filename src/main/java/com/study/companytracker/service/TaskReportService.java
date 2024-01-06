package com.study.companytracker.service;


import com.study.companytracker.dto.TaskReportDTO;
import com.study.companytracker.repository.data.TaskData;
import com.study.companytracker.util.CSVUtil;
import com.study.companytracker.util.CompanyTrackerLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskReportService {


    private final TaskData taskData;

    private final JavaMailSender mailSender;

    @Value("#{'${receivers.mails}'.split(',')}")
    private List<String> receivers;


    @Scheduled(cron = "0 0 11 * * ?")
    public void csvTasksReportSchedule() throws IOException {
        CompanyTrackerLogger.LOGGER().info("Fetching tasks report data ...");
        List<TaskReportDTO> taskReportDTOList = this.taskData.fetchTasksReportData();
        CompanyTrackerLogger.LOGGER().info("Fetched tasks report data -> {}", taskReportDTOList);

        CompanyTrackerLogger.LOGGER().info("Generating CSV report file with fetched report data");

        byte[] csvFileBytes = Files.readAllBytes(Paths.get(CSVUtil.generateTasksReportCSVFile(taskReportDTOList)));
        ByteArrayResource csvResource = new ByteArrayResource(csvFileBytes);

        this.receivers.forEach(receiver -> this.sendTaskReportMail(receiver, csvResource));
        CompanyTrackerLogger.LOGGER().info("Schedule job for tasks report finished");
    }


    private void sendTaskReportMail(String to, ByteArrayResource file) {
        MimeMessage message = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("dev@company.com");
            helper.setTo(to);
            helper.setSubject("Daily Task Reports");
            helper.setText("Please check attached file for daily tasks report");

            CompanyTrackerLogger.LOGGER().info("Adding CSV file as attachment in the mail");
            helper.addAttachment("Tasks-Report.csv", file);

            CompanyTrackerLogger.LOGGER().info("Sending mail for receiver");
            mailSender.send(message);
            CompanyTrackerLogger.LOGGER().info("Mail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
