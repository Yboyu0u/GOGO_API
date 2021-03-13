package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.community.CommentDto;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.community.Board;
import com.gogo.GoGo.domain.community.Community;
import com.gogo.GoGo.domain.community.Comment;
import com.gogo.GoGo.domain.community.Heart;
import com.gogo.GoGo.domain.record.Tag;
import com.gogo.GoGo.domain.user.User;
import com.gogo.GoGo.enumclass.PartnerStatus;
import com.gogo.GoGo.enumclass.PlaceStatus;
import com.gogo.GoGo.exception.NotExistedBoardException;
import com.gogo.GoGo.exception.NotExistedCommentException;
import com.gogo.GoGo.exception.NotExistedCommunityException;
import com.gogo.GoGo.exception.NotExistedUserIdException;
import com.gogo.GoGo.repository.BoardRepository;
import com.gogo.GoGo.repository.community.CommentRepository;
import com.gogo.GoGo.repository.community.HeartRepository;
import com.gogo.GoGo.repository.community.CommunityRepository;
import com.gogo.GoGo.repository.record.TagRepository;
import com.gogo.GoGo.repository.user.UserRepository;
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
    private HeartRepository heartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BoardRepository boardRepository;

    //조회
    public Community get(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return community;
    }

    //글쓰기
    public void create(CommunityDto dto, Long id, String nickname) {

        User user = userRepository.findById(id).orElseThrow(NotExistedUserIdException::new);
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(NotExistedBoardException::new);

        Community community = Community.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdTime(LocalDateTime.now())
                .createdBy(nickname)
                .heart(0)
                .user(user)
                .board(board)
                .build();

        communityRepository.save(community);
    }


    //TODO: record에서 써야 됨
    //    StringTokenizer tags = new StringTokenizer(dto.getTags(),"#");
//        if(dto.getTags()!=null) {
//            StringTokenizer tags = new StringTokenizer(dto.getTags(), "#");
//
//            while (tags.hasMoreTokens()) {
//                tagRepository.save(Tag.builder().name("#"+tags.nextToken()).community(community).build());
//            }
//        }

    //글수정
    public void modify(Long communityId, CommunityDto dto) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(NotExistedBoardException::new);

        community.set(dto);
        community.setBoard(board);

    }


    //TODO: record에서 써야 됨
//   tagRepository.deleteByCommunityId(communityId);

    //글삭제
    public void delete(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(NotExistedCommunityException::new);

        communityRepository.delete(community);
    }


    //댓글 달기
    public void createComment(Long userId, String userName, CommentDto dto) {

        Long communityId = dto.getCommunityId();
        String content = dto.getContent();
        Long to = dto.getTo();

        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        if(to == null){
            to =0L;
        }

        Comment comment = Comment.builder()
                .content(content)
                .createdBy(userName)
                .createdTime(LocalDateTime.now())
                .community(community)
                .user(user)
                .to(to)
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



    //좋아요 누르기
    public void pushHeart(Long userId, Long communityId) {

        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        //한 구인글에서 유저는 좋아요 한번만 누를 수 있다.
        if (heartRepository.findByUserIdAndCommunityId(userId, communityId) != null) {
            throw new RuntimeException();
        }

        community.setHeart(community.getHeart() + 1);

        Heart heart = Heart.builder()
                .user(user)
                .community(community)
                .build();
        heartRepository.save(heart);
    }

    //좋아요 취소
    public void deleteHeart(Long userId, Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(NotExistedCommunityException::new);

        if (community.getHeart() > 0) {
            community.setHeart(community.getHeart() - 1);
        } else {
            throw new RuntimeException();
        }

        heartRepository.deleteByUserIdAndCommunityId(userId, communityId);

    }

    //내가 좋아한 구인글 조회
    public List<Community> getByHeart(Long userId) {

        List<Community> communities = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        List<Heart> hearts = user.getHeartList();

        for (int i = 0; i < hearts.size(); i++) {
            Community community = hearts.get(i).getCommunity();
            communities.add(community);
        }
        return communities;

    }

    //내가 쓴글 조회
    public List<Community> searchByMy(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return user.getCommunityList();
    }

}

    //TODO: record에서 써야 됨
//    //해시태그 검색
//    public List<Community> getByTag(String tag) {
//        List<Community> communities = new ArrayList<>();
//        List<Tag> tags = new ArrayList<>();
//
//        if(tag.charAt(0)=='#'){
//            tags = tagRepository.findAllByName(tag);
//        }else{
//            tags = tagRepository.findAllByName("#"+tag);
//        }
//
//        for(int i=0; i<tags.size();i++){
//            Community community = tags.get(i).getCommunity();
//            communities.add(community);
//        }
//        return communities;
//    }


