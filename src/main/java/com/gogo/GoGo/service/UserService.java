package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(UserDto userDto){
        User user = new User();
        user.set(userDto);
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
}
