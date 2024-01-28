package com.study.companytracker.rest;


import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.TaskDTO;
import com.study.companytracker.service.TaskService;
import com.study.companytracker.util.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> fetchAllTasks(@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> addTask(@RequestBody TaskDTO taskDTO,@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.taskService.addTask(taskDTO));
    }

    @PutMapping
    public ResponseEntity<GenericRestResponse<?>> updateTask(@RequestBody TaskDTO taskDTO,@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.taskService.addTask(taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteTaskById(@PathVariable Long id,@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.taskService.deleteTaskById(id));
    }

    @GetMapping(path = "/report")
    public ResponseEntity<GenericRestResponse<?>> sendTasksReportMail(@RequestParam(value = "taskStatus", required = false) TaskStatus taskStatus,@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.taskService.sendTasksReportMail(taskStatus));
    }
}
