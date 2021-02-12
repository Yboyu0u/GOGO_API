package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.exception.AlreadyExistedEmailException;
import com.gogo.GoGo.exception.NotExistedEmailException;
import com.gogo.GoGo.exception.PasswordWrongException;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원 조회
    @Transactional
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    //회원가입
    @Transactional
    public void createUser(UserDto userDto){
        //TODO: 받은 이메일 정보가 이미 있는 계정이면 error처리
        Optional<User> existedUser = userRepository.findByEmail(userDto.getEmail());
        //이미 이메일이 있다면 error 처리
        if(existedUser.isPresent()){
            throw new AlreadyExistedEmailException();
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
      //  log.info("password: {}",encodedPassword);
        User user = new User();
        user.set(userDto);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    //로그인
    public User authenticate(String email, String password) {
        //TODO: EmailNotExistedException do
        User user = userRepository.findByEmail(email)
                .orElseThrow(NotExistedEmailException::new);

        if(passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }
}
