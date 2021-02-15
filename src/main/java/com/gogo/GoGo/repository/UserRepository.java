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
    List<User> findByName(String name);

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    @Query(value = "select user from User user where user.name = :name and user.phoneNumber = :phoneNumber")
    Optional<User> findByNameAndPhoneNumber(@Param("name") String name, @Param("phoneNumber") String phoneNumber);

    @Query(value = "select user from User user where user.email = :email and user.name = :name")
    Optional<User> findByEmailAndName(@Param("email") String email, @Param("name") String name);
}

