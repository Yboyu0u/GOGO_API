package com.gogo.GoGo.controller.user;

import com.gogo.GoGo.controller.dto.user.SessionRequestDto;
import com.gogo.GoGo.controller.dto.user.SessionResponseDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.domain.utils.JwtUtil;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


//로그인
@RequestMapping(value = "/user/session")
@RestController
@Slf4j
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //로그인을 하면 인증 확인 용으로 accesstoken을 부여 받는다.
    public ResponseEntity<SessionResponseDto>login(@RequestBody SessionRequestDto dto) throws URISyntaxException {
        String userId = dto.getUserId();
        String password = dto.getPassword();

        User user = userService.authenticate(userId,password);

        String jwt = jwtUtil.createToken(user.getId(),user.getName(),user.getNickname());

        SessionResponseDto sessionResponseDto =
                SessionResponseDto.builder().jwt(jwt).build();

        String url ="/user/session";
        return ResponseEntity.created(new URI(url))
                .body(sessionResponseDto);
    }

}
