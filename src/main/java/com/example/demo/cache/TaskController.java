package com.example.demo.cache;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Client;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskFacade taskFacade;

    @GetMapping
    public List<Client> findAll() {
        return taskFacade.findAll();
    }
}
