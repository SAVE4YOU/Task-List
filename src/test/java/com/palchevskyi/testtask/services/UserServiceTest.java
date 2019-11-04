package com.palchevskyi.testtask.services;

import com.palchevskyi.testtask.domains.User;
import com.palchevskyi.testtask.repos.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepo;

    @Test
    public void addUser() {
        User user = new User();
        userRepo.save(user);

        boolean isCreated = user.isEnabled();
        Assert.assertTrue(isCreated);
    }
}