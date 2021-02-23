package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.service.CommunityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void create(Authentication authentication, @RequestBody CommunityDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long id = claims.get("userId",Long.class);
        //String name = claims.get("name",String.class);
        String nickname = claims.get("nickname",String.class);
        communityService.create(dto,id,nickname);
    }

    //내가 쓴글 조회
    @GetMapping("/search/my")
    public List<Community> getByMy(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        return communityService.searchByMy(userId);
    }

    //분류1. 지역
    @GetMapping("/search/place/{placeId}")
    public List<Community> getByPlace(@PathVariable Long placeId){
        return communityService.searchByPlace(placeId);

    }

//   //분류2. 여행 컨셉
//    @GetMapping("/search/concept/{conceptId}")
//    public List<Community> getbyTravelConcept(){
//        return communityService.searchbyContcept();
//    }

    //분류3. 성별 분류
    //TODO:

//    //해시태그 검색
//    @PostMapping("/search/tag")
//    public List<Community> getByTag(@RequestBody SearchTagDto dto){
//        return communityService.searchByTag(dto.getTag());
//    }

    @PostMapping("/heart/{communityId}")
    public void pushHeart(Authentication authentication,@PathVariable Long communityId){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        communityService.pushHeart(userId,communityId);
    }




}
