package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    void deleteByCommunityId(Long communityId);
}
