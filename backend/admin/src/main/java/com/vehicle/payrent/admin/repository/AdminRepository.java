package com.vehicle.payrent.admin.repository;

import com.vehicle.payrent.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "select * from admin a where a.admin_id = :adminId and a.password = :password", nativeQuery = true)
    public Admin findByUserName(@Param("adminId") String adminId, @Param("password") String password);
}
