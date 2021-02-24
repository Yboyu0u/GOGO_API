package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.domain.Heart;
import com.gogo.GoGo.repository.CommunityRepository;
import com.gogo.GoGo.repository.HeartRepository;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Transactional
@Slf4j
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private HeartRepository heartRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글생성
    public Community create(CommunityDto dto, Long id,String nickname){
        Community community = new Community();
        community.set(dto);
        community.setCreatedTime(LocalDate.now());
        community.setUserId(id);
        community.setCreatedBy(nickname);

        return communityRepository.save(community);
    }

    //글수정
    public void modify(Long communityId, CommunityDto dto) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);
        community.set(dto);
    }

    //글삭제
    public void delete(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(RuntimeException::new);
        community.setDeleted(true);
        communityRepository.save(community);
    }
    //내가 쓴글 조회
    public List<Community> searchByMy(Long id) {
        return communityRepository.findAllByUserId(id);
    }

    //분류1. 지역
    public List<Community> searchByPlace(Long id) {
        return communityRepository.findAllByPlaceId(id);
    }
    //분류2. 컨셉트
    //TODO:

//    //해시태그 검색
//    public List<Community> searchByTag(String tag){
//
//        return communityRepository.findAllByTag(tag);
//    }


    //좋아요 누르기
    public void pushHeart(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);
        community.setHeart(community.getHeart()+1);
        Heart heart = Heart.builder()
                .userId(userId)
                .communityId(communityId)
                .build();
        heartRepository.save(heart);
    }

    //좋아요 취소
    public void deleteHeart(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);
        community.setHeart(community.getHeart()-1);
        heartRepository.deleteByUserIdAndCommunityId(userId,communityId);
    }

    //자기가 좋아한 구인글 조회
    public List<Community> getByHeart(Long userId) {
        List<Community> communities = new ArrayList<>();
        List<Heart> hearts = heartRepository.findAllByUserId(userId);
        for(int i=0; i<hearts.size();i++){
            Long id = hearts.get(i).getCommunityId();
            Community community = communityRepository.findById(id).orElse(null);
            communities.add(community);
        }
        return communities;
    }


}
