package com.palchevskyi.testtask.controllers;

import com.palchevskyi.testtask.domains.Task;
import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.TasksRepository;
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
    private TasksRepository tasksRepository;

    @GetMapping
    public String viewTasks(Model model) {
        Iterable<Task> tasks = tasksRepository.findAll();

        model.addAttribute("tasks", tasks);
        return "main";
    }

    @PostMapping
    public String addTask(@AuthenticationPrincipal User user, @RequestParam String text, Model model) {
        Task task = new Task(text, user);
        if (text != null && !text.isEmpty()) {
            tasksRepository.save(task);
        } else {
            model.addAttribute("msg", "Please input something");
        }
        Iterable<Task> tasks = tasksRepository.findAll();

        model.addAttribute("tasks", tasks);
        return "main";
    }


}
