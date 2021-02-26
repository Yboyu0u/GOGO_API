package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Comment;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.domain.Heart;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.repository.CommentRepository;
import com.gogo.GoGo.repository.CommunityRepository;
import com.gogo.GoGo.repository.HeartRepository;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글쓰기
    public Community create(CommunityDto dto, Long id,String nickname){
        //TODO: 존재하지 않는 계정
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        Community community = new Community();
        community.set(dto);
        community.setCreatedTime(LocalDateTime.now());
        community.setUser(user);
        community.setCreatedBy(nickname);

        return communityRepository.save(community);
    }

    //내가 쓴글 조회
    public List<Community> searchByMy(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return user.getCommunityList();
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

        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        //TODO: 존재하지 않는 구인글
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);

        community.setHeart(community.getHeart()+1);

        Heart heart = Heart.builder()
                .user(user)
                .community(community)
                .build();
        heartRepository.save(heart);
    }

    //좋아요 취소
    public void deleteHeart(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);

        if(community.getHeart()>0){
            community.setHeart(community.getHeart()-1);
        }else{
            throw new RuntimeException();
        }

       heartRepository.deleteByUserIdAndCommunityId(userId,communityId);

    }

    //내가 좋아한 구인글 조회
    public List<Community> getByHeart(Long userId) {

        List<Community> communities = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        List<Heart> hearts = user.getHeartList();

        for(int i=0; i<hearts.size();i++){
            Community community = hearts.get(i).getCommunity();
            communities.add(community);
        }
        return communities;

    }

    //댓글 달기
    public void createComment(Long userId, String userName, Long communityId, String content) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        Comment comment = Comment.builder()
                .community(community)
                .user(user)
                .userName(userName)
                .content(content)
                .createdTime(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

    }

    //댓글 보기
    public List<Comment> getComments(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(RuntimeException::new);
        return community.getCommentList();
    }

    //댓글 수정
    public void modifyComment(Long commentId, String content) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(RuntimeException::new);

        comment.setContent(content);

    }

    //댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(RuntimeException::new);

        commentRepository.delete(comment);
    }
}
