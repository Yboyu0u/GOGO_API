package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    Community save(Community community);

}
