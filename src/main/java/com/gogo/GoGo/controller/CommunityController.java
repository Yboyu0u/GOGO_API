package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/community")
@RestController
@Slf4j
public class CommunityController {
    @Autowired
    private CommunityService communityService;


    //조회
    @GetMapping("/{id}")
    public Community get(@PathVariable Long id){
        return communityService.get(id);
    }

    //글생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommunityDto dto){
        communityService.create(dto);
    }
}
