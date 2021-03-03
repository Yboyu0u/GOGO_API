package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    void deleteByCommunityId(Long communityId);

    List<Tag> findAllByName(String tag);
}
