package com.gogo.GoGo.controller;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.controller.dto.community.SearchTagDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    //태그 검색
    @PostMapping("/search/tag")
    public List<Community> getByTag(@RequestBody SearchTagDto dto){
        return communityService.searchByTag(dto.getTag());
    }


    //글생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommunityDto dto){
        communityService.create(dto);
    }

}
