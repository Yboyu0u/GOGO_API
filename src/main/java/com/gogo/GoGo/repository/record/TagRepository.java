package com.gogo.GoGo.repository.record;

import com.gogo.GoGo.domain.record.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    void deleteByRecordId(Long communityId);

    List<Tag> findAllByName(String tag);
}
