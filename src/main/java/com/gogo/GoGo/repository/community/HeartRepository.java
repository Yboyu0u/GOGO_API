package com.gogo.GoGo.repository.community;

import com.gogo.GoGo.domain.community.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart,Long> {

    List<Heart> findAllByUserId(Long userId);


    void deleteByUserIdAndCommunityId(Long userId, Long communityId);

    Heart findByUserIdAndCommunityId(Long userId, Long communityId);
}
