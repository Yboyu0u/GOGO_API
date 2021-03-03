package com.gogo.GoGo.controller.user;


import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import com.gogo.GoGo.domain.Personal;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.repository.PersonalRepository;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.PersonalService;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PersonalController {

    @Autowired
    PersonalService personalService;
    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/personal/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> create(@PathVariable Long id, @Valid @RequestBody UserPersonalDTO dto){
        personalService.create(dto,id);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
    }


    //유저 출력 확인완료.
    @GetMapping("/p/{id}")
    public User find(@PathVariable Long id) {
        List<User> userList = userRepository.findAll();
        User now = userService.getUser(id);
        return now;
    }

    @GetMapping("/p2/{id}")
    public Personal find2(@PathVariable Long id) {

        Personal personal = personalService.getPersonal(id);

        return personal;

    }



}


