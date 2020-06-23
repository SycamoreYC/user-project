package com.java.userproject.repository;

import com.java.userproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByAgeBetween(Integer lower, Integer upper);

    List<User> findAllByNameContains(String name);
}
