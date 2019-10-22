package com.palchevskyi.testtask.Controllers;

import com.palchevskyi.testtask.Domain.Task;
import com.palchevskyi.testtask.Domain.User;
import com.palchevskyi.testtask.Repos.TasksRepository;
import com.palchevskyi.testtask.Repos.UserRepository;
import com.palchevskyi.testtask.Services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "main/{id}")
public class TaskController {
    @Autowired
    private MailSender mailSender;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String editTask(@RequestParam String text, @PathVariable Long id, Model model) {
        if (tasksRepository.findTaskById(id) == null) {
            return "errorMsg";
        }
        Task task = tasksRepository.findTaskById(id);
        model.addAttribute("task", task);
        if (text != null && !text.isEmpty()) {
            task.setText(text);
        }
        tasksRepository.save(task);
        model.addAttribute("msg", "Saved!");
        return "editPage";
    }

    @GetMapping
    public String showTask(@PathVariable Long id, Model model) {
        if (tasksRepository.findTaskById(id) == null) {
            return "errorMsg";
        }
        Task task = tasksRepository.findTaskById(id);
        model.addAttribute("task", task);
        return "editPage";
    }

    @Transactional
    @PostMapping("{delete")
    public String deleteTask(@PathVariable Long id, Model model) {
        tasksRepository.deleteTaskById(id);
        Iterable<Task> tasks = tasksRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "redirect:/main";
    }

    @GetMapping("share")
    public String shareTaskView(@PathVariable Long id, Model model) {
        Task task = tasksRepository.findTaskById(id);
        model.addAttribute("task", task);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "share";
    }

    @PostMapping("/share")
    public String shareTask(@PathVariable Long id, @RequestParam String email, Model model) {
        Iterable<User> users = userRepository.findAll();
        Task task = tasksRepository.findTaskById(id);
        User user = userRepository.findUserByEmail(email);

        String message = String.format("Hello, can you please manage it afterwards?\nTask is :\"%s\".\nWith best regards, %s.",task.getText(), user.getUsername());
        if (userRepository.findUserByEmail(email) != null) {
            mailSender.send(email, "Manage task", message);
            model.addAttribute("msg", "Email sent!");
        } else if (email == null || email.isEmpty()) {
            model.addAttribute("msg3", "Please enter email");
        } else {
            model.addAttribute("msg2", "User with this email does not exists!");
        }

        return "share";
    }
}
