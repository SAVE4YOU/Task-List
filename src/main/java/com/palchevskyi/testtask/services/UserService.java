package com.palchevskyi.testtask.services;

import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

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
