package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.enumclass.PartnerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {


//    @Query(value = "select community from Community community where community.tag = :tag")
//    List<Community> findAllByTag(@Param("tag") String tag);

    List<Community> findAllByUserId(Long id);

    List<Community> findAllByPartner(PartnerStatus partner);

    List<Community> findAllByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

    List<Community> findAllByTitle(String title);
}
