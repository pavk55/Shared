package com.exam.pavk55.repos;

import com.exam.pavk55.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    @Query("select user from User as user where user.username = :username and user.password = :password")
    Optional<User> findByUsernameAndPassword(String username, String password);

}
