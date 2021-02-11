package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.SessionRequestDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//로그인
@RequestMapping(value = "api/session")
@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void loginUser(@RequestBody SessionRequestDto dto){
        String email = dto.getEmail();
        String password = dto.getPassword();

        User user = userService.authenticate(email,password);
    }

}
