package com.gogo.GoGo.repository.community;

import com.gogo.GoGo.domain.community.CommunityHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityHeartRepository extends JpaRepository<CommunityHeart,Long> {

    List<CommunityHeart> findAllByUserId(Long userId);


    void deleteByUserIdAndCommunityId(Long userId, Long communityId);

    CommunityHeart findByUserIdAndCommunityId(Long userId, Long communityId);
}
