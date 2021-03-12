package com.gogo.GoGo.repository.user;

import com.gogo.GoGo.domain.user.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Long> {

    Optional<Following> findByName(String name);
}
