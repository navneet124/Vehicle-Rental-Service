package com.vehicle.payrent.user.repository;

import com.vehicle.payrent.user.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "select * from vehicle v where v.is_booked = 'false'", nativeQuery = true)
    public List<Vehicle> findAllVehicles();
}
