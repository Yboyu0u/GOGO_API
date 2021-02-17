package com.gogo.GoGo.repository;

import com.gogo.GoGo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User save(User user);

    Optional<User> findByUserId(String userId);

    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    @Query(value = "select user from User user where user.name = :name and user.userId = :userId")
    Optional<User> findByNameAndUserId(@Param("name") String name, @Param("userId") String userId);

    @Query(value = "select user from User user where user.name = :name and user.email = :email")
    Optional<User> findByNameAndEmail(@Param("name") String name, @Param("email") String email);


}

