package com.vehicle.payrent.vendor.service;

import com.vehicle.payrent.vendor.entity.BookingDetail;
import com.vehicle.payrent.vendor.entity.Vehicle;
import com.vehicle.payrent.vendor.entity.Vendor;
import com.vehicle.payrent.vendor.repository.BookingRepository;
import com.vehicle.payrent.vendor.repository.VehicleRepository;
import com.vehicle.payrent.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VendorService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VendorRepository vendorRepository;

    public List<BookingDetail> getAllBookingDetails(){

        return bookingRepository.findAll();
    }

    @Transactional
    public List<Vehicle> getAllVehicleDetails(){

        return vehicleRepository.findAll();
    }
    @Transactional
    public Vehicle getVehicle(Integer vehicleId){
        return vehicleRepository.findById(vehicleId).get();
    }

    @Transactional
    public Vehicle updateVehicle(Integer vehicleId, Vehicle vehicle){
       // log.info("");
        Vehicle vehicle1=vehicleRepository.findById(vehicleId).get();
        if(vehicle1 != null) {
            vehicle1.setBooked(vehicle.isBooked());
            vehicle1.setRentPerday(vehicle.getRentPerday());
            vehicle1.setVehicleName(vehicle.getVehicleName());
            return vehicleRepository.save(vehicle1);
        }
        else
            throw new NoSuchElementException("VEHICLE_DOESN'T_EXISTS");
    }

    @Transactional
    public Boolean deleteVehicle(Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        if (vehicle != null) {
            vehicleRepository.deleteById(vehicleId);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean validate(String username,String password){
        Vendor registration=vendorRepository.findByUserName(username,password);
        if(registration !=null){
            return true;
        }
        //throw new NoSuchElementException("Username not found");
        return false;
    }
}
