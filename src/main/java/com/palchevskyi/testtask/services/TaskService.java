package com.palchevskyi.testtask.services;

import com.palchevskyi.testtask.domains.Task;
import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.TaskRepository;
import com.palchevskyi.testtask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    public String viewTasks(Model model){
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);
        return "main";
    }

    public String addTask(User user,String text, Model model) {
        Task task = new Task(text, user);
        if (text != null && !text.isEmpty()) {
            taskRepository.save(task);
        } else {
            model.addAttribute("msg", "Please input something");
        }
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);
        return "main";
    }

    public String editTask(String text, Long id, Model model) {
        if (taskRepository.findTaskById(id) == null) {
            return "errorMsg";
        }
        Task task = taskRepository.findTaskById(id);
        model.addAttribute("task", task);
        if (text != null && !text.isEmpty()) {
            task.setText(text);
        }
        taskRepository.save(task);
        model.addAttribute("msg", "Saved!");
        return "editPage";
    }

    public String showTask(Long id, Model model) {
        if (taskRepository.findTaskById(id) == null) {
            return "errorMsg";
        }
        Task task = taskRepository.findTaskById(id);
        model.addAttribute("task", task);
        return "editPage";
    }

    public String deleteTask(Long id, Model model) {
        taskRepository.deleteTaskById(id);
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "redirect:/main";
    }

    public String shareTaskView(@PathVariable Long id, Model model) {
        Task task = taskRepository.findTaskById(id);
        model.addAttribute("task", task);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "share";
    }

    public String shareTask(Long id, String email, Model model) {
        Task task = taskRepository.findTaskById(id);
        User user = userRepository.findUserByEmail(email);

        if (userRepository.findUserByEmail(email) != null) {
            String message = String.format("Hello, can you please manage it afterwards?\nTask is :\"%s\".\nWith best regards, %s.",task.getText(), user.getUsername());
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
