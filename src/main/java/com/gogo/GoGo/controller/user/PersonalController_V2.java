package com.gogo.GoGo.controller.user;


import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import com.gogo.GoGo.domain.Personal;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.service.PersonalService;
import com.gogo.GoGo.service.UserService;
import com.sun.activation.registries.LogSupport;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class PersonalController_V2 {

    @Autowired
    UserService userService;

    @Autowired
    PersonalService personalService;



    @GetMapping("/get/{id}")
    public User test1(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }

    //글쓰기
    @PostMapping("/test/hw/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Long id, @RequestBody UserPersonalDTO score){
       User user = userService.getUser(id);
        LogSupport.log(user.getName());
        Personal personal = new Personal();
      //  personal.setUser(user);
     //   personal.set(score);
        personalService.save(personal);
    }





}
