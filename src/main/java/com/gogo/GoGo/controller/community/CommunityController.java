package com.gogo.GoGo.controller.community;

import com.gogo.GoGo.controller.dto.community.CommentDto;
import com.gogo.GoGo.controller.dto.community.CommentModDto;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Comment;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.message.ResponseMessage;
import com.gogo.GoGo.service.CommunityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<ResponseMessage> create(Authentication authentication,@Valid @RequestBody CommunityDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long id = claims.get("userId",Long.class);
        //String name = claims.get("name",String.class);
        String nickname = claims.get("nickname",String.class);
        communityService.create(dto,id,nickname);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
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
    public ResponseEntity<ResponseMessage> modify(@PathVariable Long communityId,@Valid @RequestBody CommunityDto dto){
        communityService.modify(communityId,dto);

        return  ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //글삭제
    @DeleteMapping("/{communityId}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long communityId){
        communityService.delete(communityId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));

    }


    //좋아요 누르기
    @PostMapping("/heart/{communityId}")
    public ResponseEntity<ResponseMessage> pushHeart(Authentication authentication,@PathVariable Long communityId){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        communityService.pushHeart(userId,communityId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //좋아요 취소
    @DeleteMapping("/heart/{communityId}")
    public ResponseEntity<ResponseMessage> deleteHeart(Authentication authentication,@PathVariable Long communityId){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        communityService.deleteHeart(userId,communityId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
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
    public ResponseEntity<ResponseMessage> createComment(Authentication authentication, @Valid @RequestBody CommentDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        String userName = claims.get("nickname",String.class);

        Long communityId = dto.getCommunityId();
        String content = dto.getContent();

        communityService.createComment(userId,userName,communityId,content);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //댓글 보기
    @GetMapping("/comment/{communityId}")
    public List<Comment> getComments(@PathVariable Long communityId){
        return communityService.getComments(communityId);
    }

    //댓글 수정
    @PatchMapping("/comment")
    public ResponseEntity<ResponseMessage> modifyComment(@Valid @RequestBody CommentModDto dto){

        Long commentId = dto.getCommentId();
        String content = dto.getContent();

        communityService.modifyComment(commentId,content);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable Long commentId){
        communityService.deleteComment(commentId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

}
