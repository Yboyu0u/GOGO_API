package com.gogo.GoGo.service;

import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.repository.PersonalRepository;
import com.gogo.GoGo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

public class PersonalServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void save() {

        User user = userService.getUser(1L);
        Assertions.assertEquals(user.getName(),"HW");
    }

    @Test
    void create() {
    }

    @Test
    void findMatch() {
        User user = userService.getUser(1L);


    }
}
