package com.palchevskyi.testtask.Controllers;

import com.palchevskyi.testtask.Domain.Task;
import com.palchevskyi.testtask.Domain.User;
import com.palchevskyi.testtask.Repos.TasksRepository;
import com.palchevskyi.testtask.Repos.UserRepository;
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
