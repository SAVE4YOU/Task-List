package com.palchevskyi.testtask.controllers;

import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.UserRepository;
import com.palchevskyi.testtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        return userService.addUser(user,model);
    }
}
