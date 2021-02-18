package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.CheckUserIdDto;
import com.gogo.GoGo.controller.dto.CheckNicknameDto;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/check")
@RestController
@Slf4j
public class CheckController {

    @Autowired
    private UserService userService;

    //이메일 중복 확인
    @PostMapping("/email")
    public void checkUserId(@RequestBody CheckUserIdDto dto) {
        userService.checkUserId(dto.getUserId());
    }

    //닉네임 중복 확인
    @PostMapping("/nickname")
    public void checkNickname(@RequestBody CheckNicknameDto dto){
        userService.checkNickname(dto.getNickname());
    }

}
