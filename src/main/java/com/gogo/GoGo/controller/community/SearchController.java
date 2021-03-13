package com.gogo.GoGo.controller.community;


import com.gogo.GoGo.controller.dto.community.SearchDto;
import com.gogo.GoGo.domain.community.Community;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/community/search")
@RestController
@Slf4j
public class SearchController {

    @Autowired
    private CommunityService communityService;


    @PostMapping("/{boardId}")
    public List<Community> getByTitleAndContent(@PathVariable Long boardId, @RequestBody SearchDto dto){
        return communityService.getByTitleAndContent(boardId, dto.getKeyword());
    }


    //TODO:record에서 써야됨
//    //해시태그 검색
//    @PostMapping("/tag")
//    public List<Community> getByTag(@RequestBody TagDto dto){
//        return communityService.getByTag(dto.getTag());
//    }



}
