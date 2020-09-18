package com.vehicle.payrent.user.repository;

import com.vehicle.payrent.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user u where u.username = ? and u.password = ?", nativeQuery = true)
    public User findByUserName(String username, String password);

    @Query(value = "select * from user u where u.username = ?", nativeQuery = true)
    User findByUser(String username);

    @Query(value = "select user_Id from user u where u.username = ?", nativeQuery = true)
    User getUserId(String username);
}
