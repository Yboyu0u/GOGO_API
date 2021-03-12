package com.gogo.GoGo.repository;

import com.gogo.GoGo.enumclass.PlaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    void deleteByCommunityId(Long communityId);

    List<Place> findByName(PlaceStatus name);
}
