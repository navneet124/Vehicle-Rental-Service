package com.vehicle.payrent.admin.service;

import com.vehicle.payrent.admin.entity.*;
import com.vehicle.payrent.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Boolean validate(String adminId,String password){

        Admin registration=adminRepository.findByUserName(adminId,password);
        if(registration !=null){
            return true;
        }
        //throw new NoSuchElementException("Username not found");
        return false;
    }

    @Transactional
    public Vehicle addVehicle(Vehicle vehicle){

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle updateVehicle(Integer vehicleId, Vehicle vehicle){
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
    public Boolean deleteVehicle(Integer vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        if (vehicle != null) {
            vehicleRepository.deleteById(vehicleId);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();

    }

    @Transactional
    public List<BookingDetail> getAllBookingDetails(){
        return bookingRepository.findAll();

    }

    @Transactional
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Transactional
    public Vendor addVendor(Vendor vendor){

         return vendorRepository.save(vendor);
    }

    @Transactional
    public Vendor updateVendor(Integer vendorId, Vendor vendor){

        Vendor vendor1=vendorRepository.findById(vendorId).get();
        if(vendor1 != null) {
            vendor1.setUsername(vendor.getUsername());
            vendor1.setAddress(vendor.getAddress());
            vendor1.setPassword(vendor.getPassword());
            vendor1.setVendorName(vendor.getVendorName());
            vendor1.setPhone(vendor.getPhone());
            return vendorRepository.save(vendor1);
        }
        else
            throw new NoSuchElementException("VEHICLE_DOESN'T_EXISTS");
    }

    @Transactional
    public Boolean deleteVendor(Integer vendorId){

        Vendor vendor = vendorRepository.findById(vendorId).get();
        if (vendor != null) {
            vendorRepository.deleteById(vendorId);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Vendor> getAllVendor(){
        return vendorRepository.findAll();
    }

    @Transactional
    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    @Transactional
    public Vendor getVendor(Integer vendorId){
        return vendorRepository.findById(vendorId).get();
    }
}
