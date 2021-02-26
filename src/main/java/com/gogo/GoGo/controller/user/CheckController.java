package com.gogo.GoGo.controller.user;

import com.gogo.GoGo.controller.dto.user.CheckUserIdDto;
import com.gogo.GoGo.controller.dto.user.CheckNicknameDto;
import com.gogo.GoGo.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = {"4. 이메일, 닉네임 중복확인"})
@RequestMapping(value = "/api/check")
@RestController
@Slf4j
public class CheckController {

    @Autowired
    private UserService userService;

    //이메일 중복 확인
    @PostMapping("/userId")
    public ResponseEntity<String> checkUserId(@RequestBody CheckUserIdDto dto) {
        userService.checkUserId(dto.getUserId());

        return ResponseEntity.ok("ok");
    }

    //닉네임 중복 확인
    @PostMapping("/nickname")
    public ResponseEntity<String> checkNickname(@RequestBody CheckNicknameDto dto){
        userService.checkNickname(dto.getNickname());

        return ResponseEntity.ok("ok");
    }

}
