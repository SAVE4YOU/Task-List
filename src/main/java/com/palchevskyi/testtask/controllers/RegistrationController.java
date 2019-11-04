package com.palchevskyi.testtask.controllers;

import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        if(user.getEmail()==null||user.getEmail().isEmpty()||user.getUsername()==null||user.getUsername().isEmpty()||user.getPassword()==null||user.getPassword().isEmpty()){
            model.addAttribute("message2", "Please fill all fields!");
            return "registration";
        }

        userRepo.save(user);

        return "redirect:/login";
    }
}
