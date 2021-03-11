package io.github.wolejarz.controller;

import io.github.wolejarz.model.Task;
import io.github.wolejarz.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }
    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity <List<Task>> readAllTasks() {
        logger.warn("Exposing All Records");
        return  ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/tasks")
    ResponseEntity <List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pageable");
        return  ResponseEntity.ok(repository.findAll(page).getContent());
    }
}
