package com.palchevskyi.testtask.controllers;

import com.palchevskyi.testtask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "main/{id}")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public String editTask(@RequestParam String text, @PathVariable Long id, Model model) {
        return taskService.editTask(text, id, model);
    }

    @GetMapping
    public String showTask(@PathVariable Long id, Model model) {
        return taskService.showTask(id, model);
    }

    @Transactional
    @PostMapping("{delete")
    public String deleteTask(@PathVariable Long id, Model model) {
        return taskService.deleteTask(id, model);
    }

    @GetMapping("share")
    public String shareTaskView(@PathVariable Long id, Model model) {
        return taskService.shareTaskView(id, model);
    }

    @PostMapping("/share")
    public String shareTask(@PathVariable Long id, @RequestParam String email, Model model) {
        return taskService.shareTask(id, email, model);
    }
}
