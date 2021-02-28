package com.gogo.GoGo.controller.user;

import com.gogo.GoGo.controller.dto.user.ImgDto;
import com.gogo.GoGo.controller.dto.user.ModUserDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"1. 회원가입 및 유저 관련"})
@RequestMapping(value = "/api/user")
@RestController
@Slf4j
public class UserController{
    @Autowired
    private UserService userService;


    //회원 정보 조회

    @ApiOperation(value = "회원 정보 조회", notes = "회원 아이디를 통해 유저정보 조회한다")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    //회원가입를
    @ApiOperation(value = "회원가입", notes = "회원가입.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 수정
    @ApiOperation(value = "프로필 수정하기", notes = "프로필 수정하기")
    @PatchMapping("/patch")
    public ResponseEntity<ResponseMessage> modifyUser(Authentication authentication, @RequestBody ModUserDto userDto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.modifyPerson(userId,userDto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 사진 업로드
    @ApiOperation(value = "프로필 사진 업로드", notes = "프로필 사진을 등록합니다.")
    @PutMapping("/{id}")
    public void uploadImg(@PathVariable Long id, @RequestBody ImgDto dto){
        userService.uploadImg(id,dto.getImg());
    }

    //회원탈퇴
    @ApiOperation(value = "유저 삭제", notes = "유저를 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteUser(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.deleteUser(userId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }
}
