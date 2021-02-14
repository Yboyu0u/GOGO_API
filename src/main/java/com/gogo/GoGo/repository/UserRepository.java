package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);

    User save(User user);

    Optional<User> findByEmail(String email);
}

