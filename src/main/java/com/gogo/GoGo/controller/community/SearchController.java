package com.gogo.GoGo.controller.community;


import com.gogo.GoGo.controller.dto.community.DateDto;
import com.gogo.GoGo.controller.dto.community.PartnerDto;
import com.gogo.GoGo.controller.dto.community.PlaceDto;
import com.gogo.GoGo.controller.dto.community.TagDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/community/search")
@RestController
@Slf4j
public class SearchController {

    @Autowired
    private CommunityService communityService;

    //지역 조회
    // SEOUL(서울),GYEONGGI(경기),CHUNG(충청),GYEONGSANG(경상),JEOLLA(전라),GANGWON(강원),JEJU(제주) NOTHING(상관없음)
    @PostMapping("/place")
    public List<Community> getByPlace(@RequestBody PlaceDto dto){
        return communityService.getByPlace(dto.getPlace());
    }

    //성별 조회
    @PostMapping("/partner")
    public List<Community> getByPartner(@RequestBody PartnerDto dto){
        return communityService.getByPartner(dto.getPartner());
    }

    //날짜 조회
    @PostMapping("/date")
    public List<Community> getByDate(@RequestBody DateDto dto){
        return communityService.getByDate(dto.getStartDate(),dto.getEndDate());
    }

    //해시태그 검색
    @PostMapping("/tag")
    public List<Community> getByTag(@RequestBody TagDto dto){
        return communityService.getByTag(dto.getTag());
    }



}
