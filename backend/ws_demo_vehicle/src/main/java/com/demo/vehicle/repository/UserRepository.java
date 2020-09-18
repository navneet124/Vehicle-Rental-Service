package com.demo.vehicle.repository;

import com.demo.vehicle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select * from user u where u.username = ? and u.password = ?", nativeQuery = true)
    User findByUserName(String username, String password);

    @Query(value = "select * from user u where u.username = ?", nativeQuery = true)
    User findByUser(String username);
}
