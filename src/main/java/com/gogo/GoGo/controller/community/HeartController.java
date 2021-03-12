//package com.gogo.GoGo.controller.community;
//
//import com.gogo.GoGo.domain.community.Community;
//import com.gogo.GoGo.message.ResponseMessage;
//import com.gogo.GoGo.service.CommunityService;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping(value = "/community/heart")
//@RestController
//@Slf4j
//public class HeartController {
//
//    @Autowired
//    private CommunityService communityService;
//
//    //좋아요 누르기
//    @PostMapping("/{communityId}")
//    public ResponseEntity<ResponseMessage> pushHeart(Authentication authentication, @PathVariable Long communityId) {
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId", Long.class);
//        communityService.pushHeart(userId, communityId);
//
//        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "ok"));
//    }
//
//    //자기가 좋아한 구인글 조회
//    @GetMapping
//    public List<Community> getByHeart(Authentication authentication) {
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId", Long.class);
//        return communityService.getByHeart(userId);
//    }
//
//    //좋아요 취소
//    @DeleteMapping("/{communityId}")
//    public ResponseEntity<ResponseMessage> deleteHeart(Authentication authentication, @PathVariable Long communityId) {
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId", Long.class);
//        communityService.deleteHeart(userId, communityId);
//
//        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "ok"));
//    }
//
//
//}
