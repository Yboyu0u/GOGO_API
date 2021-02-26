package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    Community save(Community community);

    List<Community> findAllByPlaceId(Long placeId);

//    @Query(value = "select community from Community community where community.tag = :tag")
//    List<Community> findAllByTag(@Param("tag") String tag);

    List<Community> findAllByUserId(Long id);
}
