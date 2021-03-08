package com.gogo.GoGo.controller.user;

import com.gogo.GoGo.controller.dto.user.ModUserDto;
import com.gogo.GoGo.controller.dto.user.PasswordDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserController{
    @Autowired
    private UserService userService;


    //회원 정보 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    //회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 수정
    @PatchMapping("/patch")
    public ResponseEntity<ResponseMessage> modifyUser(Authentication authentication, @RequestBody ModUserDto userDto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.modifyPerson(userId,userDto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 사진 업로드
    @PostMapping("/uploadImg")
    public ResponseEntity<ResponseMessage> uploadImg(Authentication authentication, @RequestParam("data") MultipartFile file) throws IOException{
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        userService.uploadImg(userId,file);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }


    //회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteUser(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.deleteUser(userId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //비밀번호 변경
    @PostMapping("/change/password")
    public ResponseEntity<ResponseMessage> chagePw(Authentication authentication, @RequestBody PasswordDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.changePw(userId,dto.getPassword());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }
}

