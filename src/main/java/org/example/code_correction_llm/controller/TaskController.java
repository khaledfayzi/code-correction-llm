package org.example.code_correction_llm.controller;

import java.util.List;

import org.example.code_correction_llm.model.Task;
import org.example.code_correction_llm.repository.TaskRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 🔹 Professor speichert Aufgabe
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // 🔹 Student lädt Aufgaben
    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}