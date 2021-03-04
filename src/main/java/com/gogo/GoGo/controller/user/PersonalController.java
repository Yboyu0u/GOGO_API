package com.gogo.GoGo.controller.user;


import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import com.gogo.GoGo.domain.Personal;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.repository.PersonalRepository;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.PersonalService;
import com.gogo.GoGo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    @ApiOperation(value = "성향 입력" , notes = "동작 확인 완료")
    @PostMapping("/personal/test/{id}")
    public User SetPersonal(@PathVariable Long id , @RequestBody UserPersonalDTO dto) {
        User user = userService.getUser(id);
        Personal personal = personalService.UserSetPersonal(dto);
        user.setPersonal(personal);
        userRepository.save(user);
        return user;
    }

    @ApiOperation(value = "매칭 임" , notes = "동작 확인 x")
    @GetMapping("/p2")
    public Personal find(Long id) {
        Personal personal = personalService.Find(id);
        return personal;
    }








}


