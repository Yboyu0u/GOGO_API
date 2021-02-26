package com.gogo.GoGo.controller.community;

import com.gogo.GoGo.controller.dto.community.CommentDto;
import com.gogo.GoGo.controller.dto.community.CommentModDto;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Comment;
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


    //글쓰기
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
    @GetMapping
    public List<Community> getByMy(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        return communityService.searchByMy(userId);
    }

    //글수정
    @PatchMapping("/{communityId}")
    public void modify(@PathVariable Long communityId,@RequestBody CommunityDto dto){
        communityService.modify(communityId,dto);
    }

    //글삭제
    @DeleteMapping("/{communityId}")
    public void delete(@PathVariable Long communityId){
        communityService.delete(communityId);
    }


    //좋아요 누르기
    @PostMapping("/heart/{communityId}")
    public void pushHeart(Authentication authentication,@PathVariable Long communityId){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        communityService.pushHeart(userId,communityId);
    }

    //좋아요 취소
    @DeleteMapping("/heart/{communityId}")
    public void deleteHeart(Authentication authentication,@PathVariable Long communityId){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        communityService.deleteHeart(userId,communityId);
    }

    //자기가 좋아한 구인글 조회
    @GetMapping("/heart")
    public List<Community> getByHeart(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        return communityService.getByHeart(userId);
    }

    //댓글 달기
    @PostMapping("/comment")
    public void createComment(Authentication authentication, @RequestBody CommentDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        String userName = claims.get("nickname",String.class);

        Long communityId = dto.getCommunityId();
        String content = dto.getContent();

        communityService.createComment(userId,userName,communityId,content);
    }

    //댓글 보기
    @GetMapping("/comment/{communityId}")
    public List<Comment> getComments(@PathVariable Long communityId){
        return communityService.getComments(communityId);
    }

    //댓글 수정
    @PatchMapping("/comment")
    public void modifyComment(@RequestBody CommentModDto dto){

        Long commentId = dto.getCommentId();
        String content = dto.getContent();

        communityService.modifyComment(commentId,content);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        communityService.deleteComment(commentId);
    }



    //분류1. 지역
//    @GetMapping("/search/place/{placeId}")
//    public List<Community> getByPlace(@PathVariable Long placeId){
//        return communityService.searchByPlace(placeId);
//
//    }

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





}
