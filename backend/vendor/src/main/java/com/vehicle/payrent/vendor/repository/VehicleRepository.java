package com.vehicle.payrent.vendor.repository;

import com.vehicle.payrent.vendor.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "select * from vehicle v where v.is_booked = 'false'", nativeQuery = true)
    public List<Vehicle> findAllVehicles();
}
