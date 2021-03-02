package com.gogo.GoGo.controller.user;


import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.PersonalService;
import com.gogo.GoGo.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/personal/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> create(@PathVariable Long id, @Valid @RequestBody UserPersonalDTO dto){
      //  Claims claims = (Claims) authentication.getPrincipal();
      //  Long id = claims.get("userId",Long.class);
        //String name = claims.get("name",String.class);

        personalService.create(dto,id);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
    }

    @GetMapping("/personal")
    public String match() {
        User us = new User();
        us = personalService.Match();
        return us.getName();
    }


    @GetMapping("/personal/2")
    public String match(Long id) {
        User us = new User();
        us = userService.getUser(id);
        return us.getName();
    }


    @GetMapping("/personal/{id}")
    public User match2(@PathVariable Long id) {
        User us = new User();
        us = userService.getUser(id);


        User all = personalService.FindMatch(id);

        return all;

    }
}
