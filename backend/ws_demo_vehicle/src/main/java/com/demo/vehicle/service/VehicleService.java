package com.demo.vehicle.service;

import com.demo.vehicle.entity.Vehicle;
import com.demo.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getALlVehicles(){
        return vehicleRepository.findAll();
    }
}
