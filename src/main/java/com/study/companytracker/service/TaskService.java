package com.study.companytracker.service;

import com.study.companytracker.converter.TaskConverter;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.MailAttachmentDTO;
import com.study.companytracker.dto.TaskDTO;
import com.study.companytracker.dto.TaskReportDTO;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Task;
import com.study.companytracker.repository.data.TaskData;
import com.study.companytracker.util.CSVUtil;
import com.study.companytracker.util.enums.ResponseMessage;
import com.study.companytracker.util.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskData taskData;

    private final MailService mailService;

    @Value("#{'${receivers.mails}'.split(',')}")
    private List<String> receivers;

    public GenericRestResponse<?> getAllTasks() {
        return GenericRestResponse.builder()
                .data(this.taskData.findAll().stream().map(TaskConverter::toDto))
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> addTask(TaskDTO taskDTO) {
        Task task = this.taskData.save(TaskConverter.toEntity(taskDTO));
        return GenericRestResponse.builder()
                .data(task)
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> deleteTaskById(Long id) {
        Optional<Task> task = this.taskData.findById(id);
        if (task.isPresent())
            this.taskData.deleteById(id);
        else
            throw new NotFoundException("No tasks Found with this Id: " + id);
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }


    //@Scheduled(cron = "0 * * * * *") // Run every minute
    @Scheduled(cron = "0 0 11 * * ?") // This method will be executed every day at 11 am
    private void csvTasksReportScheduledMailJob() {
        this.sendTasksReportMail(null);
    }

    public GenericRestResponse<?> sendTasksReportMail(TaskStatus taskStatus) {
        List<TaskReportDTO> taskReportDTOList = this.taskData.fetchTasksReportData(taskStatus);
        ByteArrayResource csvResource = CSVUtil.generateTasksReportCSVFile(taskReportDTOList);
        this.receivers.forEach(receiver -> this.mailService.sendMail(
                        receiver,
                        "Daily Tasks Report",
                        "Please check attached file for daily tasks report",
                        Collections.singletonList(
                                MailAttachmentDTO.builder()
                                        .attachmentName("Tasks-Report.csv")
                                        .attachmentResource(csvResource)
                                        .build()
                        )
                )
        );
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }
}
