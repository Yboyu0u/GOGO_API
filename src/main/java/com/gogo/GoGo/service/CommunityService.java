package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.*;
import com.gogo.GoGo.enumclass.PlaceStatus;
import com.gogo.GoGo.exception.NotExistedCommentException;
import com.gogo.GoGo.exception.NotExistedCommunityException;
import com.gogo.GoGo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PlaceRepository placeRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글쓰기
    public void create(CommunityDto dto, Long id,String nickname){
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        StringTokenizer places = new StringTokenizer(dto.getPlaces(),",");

        Community community = new Community();
        community.set(dto);
        community.setUser(user);
        community.setCreatedTime(LocalDateTime.now());
        community.setCreatedBy(nickname);
        community.setHeart(0);
        community = communityRepository.save(community);

        while(places.hasMoreTokens()){
            placeRepository.save(Place.builder().name(PlaceStatus.valueOf(places.nextToken())).community(community).build());
        }
    }

    //내가 쓴글 조회
    public List<Community> searchByMy(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return user.getCommunityList();
    }

    //글수정
    public void modify(Long communityId, CommunityDto dto) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        placeRepository.deleteByCommunityId(communityId);

        if(dto.getPlaces()!=null){
            StringTokenizer places = new StringTokenizer(dto.getPlaces(),",");
            while(places.hasMoreTokens()){
                placeRepository.save(Place.builder().name(PlaceStatus.valueOf(places.nextToken())).community(community).build());
            }
        }

        community.set(dto);
    }

    //글삭제
    public void delete(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(NotExistedCommunityException::new);

        placeRepository.deleteByCommunityId(communityId);
        communityRepository.delete(community);


    }

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
                .orElseThrow(NotExistedCommunityException::new);

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
                .orElseThrow(NotExistedCommentException::new);
        return community.getCommentList();
    }

    //댓글 수정
    public void modifyComment(Long commentId, String content) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NotExistedCommentException::new);

        comment.setContent(content);

    }

    //댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(NotExistedCommentException::new);

        commentRepository.delete(comment);
    }
}
