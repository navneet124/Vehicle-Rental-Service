package com.vehicle.payrent.vendor.repository;

import com.vehicle.payrent.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    @Query(value = "select * from vendor v where v.username = ? and v.password = ?", nativeQuery = true)
    public Vendor findByUserName(String username, String password);
}
