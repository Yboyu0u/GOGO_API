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

    Optional<User> findByNickname(String nickname);

    @Query(value = "select user from User user where user.userId = :userId and user.nickname = :nickname")
    Optional<User> findByUserIdAndNickname(@Param("userId") String userId, @Param("nickname") String nickname);

    @Query(value = "select user from User user where user.name = :name and user.userId = :userId")
    Optional<User> findByNameAndUserId(@Param("name") String name, @Param("userId") String userId);

    @Query(value = "select user from User user where user.name = :name and user.phoneNumber = :phoneNumber")
    Optional<User> findByNameAndPhoneNumber(@Param("name") String name, @Param("phoneNumber") String phoneNumber);


}

