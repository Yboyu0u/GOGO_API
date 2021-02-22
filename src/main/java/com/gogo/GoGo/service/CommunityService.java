package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.repository.CommunityRepository;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글생성
    @Transactional
    public Community create(CommunityDto dto){
        Community community = new Community();
        community.set(dto);
        community.setCreatedTime(LocalDate.now());
        return communityRepository.save(community);
    }

    //분류1. 지역
    public List<Community> searchByPlace(Long id) {
        return communityRepository.findAllByPlaceId(id);
    }
    //분류2. 컨셉트
    //TODO:

    //태그검색
    public List<Community> searchByTag(String tag){
        System.out.println(tag);
        return communityRepository.findAllByTag(tag);
        //TODO: 없으면 error 처리
    }


}
