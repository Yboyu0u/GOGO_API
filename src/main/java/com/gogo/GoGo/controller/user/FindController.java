package com.gogo.GoGo.controller.user;

import com.gogo.GoGo.controller.dto.user.FindUserIDRequestDto;
import com.gogo.GoGo.controller.dto.user.FindUserIdResponseDto;
import com.gogo.GoGo.controller.dto.user.FindPasswordRequestDto;
import com.gogo.GoGo.controller.dto.user.FindPasswordResponseDto;
import com.gogo.GoGo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;


@Api(tags = {"2. 아이디, 비밀번호 찾기"})
@RequestMapping(value = "/api/find")
@RestController
@Slf4j
public class FindController {
    @Autowired
    private UserService userService;

    //userId 찾기
    @PostMapping("/userId")
    @ApiOperation(value = " 유저아이디 찾기.", notes = "아이디찾기.")
    public ResponseEntity<FindUserIdResponseDto> findUserId(@RequestBody FindUserIDRequestDto dto) throws URISyntaxException {
        String userId =  userService.findUserId(dto.getName(), dto.getPhoneNumber());

        FindUserIdResponseDto responseDto = FindUserIdResponseDto.builder()
                .userId(userId)
                .build();

        String url ="/api/find/userId";
        return ResponseEntity.created(new URI(url))
                .body(responseDto);
    }

    //password 찾기
    @PostMapping("/password")
    @ApiOperation(value = "유저 비밀번호 찾기", notes = "비밀번호 찾기.")
    public ResponseEntity<FindPasswordResponseDto> findPassword(@RequestBody FindPasswordRequestDto dto) throws URISyntaxException {
        String password = userService.findPassword(dto.getName(),dto.getUserId());

        FindPasswordResponseDto responseDto = FindPasswordResponseDto.builder()
                .password(password)
                .build();

        String url = "/api/find/password";
        return ResponseEntity.created(new URI(url))
                .body(responseDto);

    }
    //TODO: findPassword
}
