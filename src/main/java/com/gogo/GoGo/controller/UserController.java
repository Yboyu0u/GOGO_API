package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/api/user")
@RestController
@Slf4j
public class UserController{
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
    }
}
