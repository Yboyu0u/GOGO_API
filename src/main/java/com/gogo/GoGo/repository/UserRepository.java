package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);

    User save(User user);
}

