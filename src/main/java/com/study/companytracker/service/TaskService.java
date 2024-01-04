package com.study.companytracker.service;

import com.study.companytracker.converter.TaskConverter;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.TaskDTO;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Task;
import com.study.companytracker.repository.data.TaskData;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskData taskData;
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
}
