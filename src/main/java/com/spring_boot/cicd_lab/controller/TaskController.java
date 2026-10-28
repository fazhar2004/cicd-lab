package com.spring_boot.cicd_lab.controller;

import com.spring_boot.cicd_lab.dto.CreateTaskRequest;
import com.spring_boot.cicd_lab.model.Task;
import com.spring_boot.cicd_lab.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        return taskService.create(request.title());
    }
}
