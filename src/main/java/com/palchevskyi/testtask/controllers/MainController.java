package com.palchevskyi.testtask.controllers;

import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String viewTasks(Model model) {
        return taskService.viewTasks(model);
    }

    @PostMapping
    public String addTask(@AuthenticationPrincipal User user, @RequestParam String text, Model model) {
        return taskService.addTask(user,text,model);
    }
}
