package com.gogo.GoGo.controller.community;

import com.gogo.GoGo.controller.dto.community.CommentDto;
import com.gogo.GoGo.controller.dto.community.ModCommentDto;
import com.gogo.GoGo.domain.community.Comment;
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

@RequestMapping(value = "/community/comment")
@RestController
@Slf4j
public class CommentController {

    @Autowired
    private CommunityService communityService;

    //댓글 달기
    @PostMapping
    public ResponseEntity<ResponseMessage> createComment(Authentication authentication, @Valid @RequestBody CommentDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        String userName = claims.get("nickname",String.class);

        communityService.createComment(userId,userName,dto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //댓글 보기
    @GetMapping("/{communityId}")
    public List<Comment> getComments(@PathVariable Long communityId){
        return communityService.getComments(communityId);
    }

    //댓글 수정
    @PatchMapping
    public ResponseEntity<ResponseMessage> modifyComment(@Valid @RequestBody ModCommentDto dto){

        Long commentId = dto.getCommentId();
        String content = dto.getContent();

        communityService.modifyComment(commentId,content);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable Long commentId){
        communityService.deleteComment(commentId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }


}
