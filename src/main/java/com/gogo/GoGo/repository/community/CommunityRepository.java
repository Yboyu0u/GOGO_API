package com.gogo.GoGo.repository.community;

import com.gogo.GoGo.domain.community.Community;
import com.gogo.GoGo.enumclass.PartnerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {


    List<Community> findAllByUserId(Long id);

    List<Community> findAllByTitle(String title);

    List<Community> findByBoardId(Long boardId);

    List<Community> findByTitleContaining(String keyword);

    List<Community> findByBoardIdAndTitleContaining(Long boardId, String keyword);
}
