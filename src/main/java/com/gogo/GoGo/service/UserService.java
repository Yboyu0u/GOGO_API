package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public void createUser(UserDto userDto){
        //TODO: 받은 이메일 정보가 이미 있는 계정이면 error처리

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.set(userDto);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    //로그인
    public User authenticate(String email, String password) {
        //TODO: EmailNotExistedException do
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException());

        return user;
    }
}
