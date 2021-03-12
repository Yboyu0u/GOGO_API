package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.*;
import com.gogo.GoGo.domain.community.Community;
import com.gogo.GoGo.domain.community.Comment;
import com.gogo.GoGo.domain.community.Heart;
import com.gogo.GoGo.domain.record.Tag;
import com.gogo.GoGo.domain.user.User;
import com.gogo.GoGo.enumclass.PartnerStatus;
import com.gogo.GoGo.enumclass.PlaceStatus;
import com.gogo.GoGo.exception.NotExistedCommentException;
import com.gogo.GoGo.exception.NotExistedCommunityException;
import com.gogo.GoGo.repository.*;
import com.gogo.GoGo.repository.community.CommunityCommentRepository;
import com.gogo.GoGo.repository.community.CommunityHeartRepository;
import com.gogo.GoGo.repository.community.CommunityRepository;
import com.gogo.GoGo.repository.record.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private CommunityHeartRepository communityHeartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityCommentRepository communityCommentRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TagRepository tagRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글쓰기
    public void create(CommunityDto dto, Long id, String nickname){

        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        Community community = new Community();
        community.set(dto);
        community.setUser(user);
        community.setCreatedTime(LocalDateTime.now());
        community.setCreatedBy(nickname);
        community.setHeart(0);
        community = communityRepository.save(community);

        StringTokenizer places = new StringTokenizer(dto.getPlaces(),",");
        while(places.hasMoreTokens()){
            placeRepository.save(Place.builder().name(PlaceStatus.valueOf(places.nextToken())).community(community).build());
        }

        if(dto.getTags()!=null) {
            StringTokenizer tags = new StringTokenizer(dto.getTags(), "#");

            while (tags.hasMoreTokens()) {
                tagRepository.save(Tag.builder().name("#"+tags.nextToken()).community(community).build());
            }
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
        tagRepository.deleteByCommunityId(communityId);

        StringTokenizer places = new StringTokenizer(dto.getPlaces(),",");
        while(places.hasMoreTokens()) {
            placeRepository.save(Place.builder().name(PlaceStatus.valueOf(places.nextToken())).community(community).build());
        }

        StringTokenizer tags = new StringTokenizer(dto.getTags(),"#");
        if(dto.getTags()!=null){
            while(tags.hasMoreTokens()){
                tagRepository.save(Tag.builder().name("#"+tags.nextToken()).community(community).build());
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

        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        //한 구인글에서 유저는 좋아요 한번만 누를 수 있다.
        if(communityHeartRepository.findByUserIdAndCommunityId(userId,communityId) != null){
            throw new RuntimeException();
        }

        community.setHeart(community.getHeart()+1);

        Heart heart = Heart.builder()
                .user(user)
                .community(community)
                .build();
        communityHeartRepository.save(heart);
    }

    //좋아요 취소
    public void deleteHeart(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        if(community.getHeart()>0){
            community.setHeart(community.getHeart()-1);
        }else{
            throw new RuntimeException();
        }

       communityHeartRepository.deleteByUserIdAndCommunityId(userId,communityId);

    }

    //내가 좋아한 구인글 조회
    public List<Community> getByHeart(Long userId) {

        List<Community> communities = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        List<Heart> hearts = user.getHeartList();

        for(int i = 0; i< hearts.size(); i++){
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

        communityCommentRepository.save(comment);

    }

    //댓글 보기
    public List<Comment> getComments(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommentException::new);
        return community.getCommentList();
    }

    //댓글 수정
    public void modifyComment(Long commentId, String content) {

        Comment comment = communityCommentRepository.findById(commentId)
                .orElseThrow(NotExistedCommentException::new);

        comment.setContent(content);
    }

    //댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = communityCommentRepository.findById(commentId)
                .orElseThrow(NotExistedCommentException::new);

        communityCommentRepository.delete(comment);
    }

    //지역별 조회
    public List<Community> getByPlace(PlaceStatus place) {
        List<Community> communities = new ArrayList<>();
        List<Place> places = placeRepository.findByName(place);
        for(int i =0; i<places.size();i++){
            communities.add(places.get(i).getCommunity());
        }

        return communities;
    }

    //성별 조회
    public List<Community> getByPartner(PartnerStatus partner) {
        return communityRepository.findAllByPartner(partner);
    }

    //날짜별 조회
    public List<Community> getByDate(LocalDate startDate, LocalDate endDate) {
        return communityRepository.findAllByStartDateAndEndDate(startDate,endDate);
    }

    //해시태그 검색
    public List<Community> getByTag(String tag) {
        List<Community> communities = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();

        if(tag.charAt(0)=='#'){
            tags = tagRepository.findAllByName(tag);
        }else{
            tags = tagRepository.findAllByName("#"+tag);
        }

        for(int i=0; i<tags.size();i++){
            Community community = tags.get(i).getCommunity();
            communities.add(community);
        }
        return communities;
    }

}
