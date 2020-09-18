package com.vehicle.payrent.admin.controller;

import com.vehicle.payrent.admin.entity.*;
import com.vehicle.payrent.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<HttpStatus> adminLogin(@RequestBody AdminLogin adminlogin){

        Boolean b =   adminService.validate(adminlogin.getAdminId(), adminlogin.getPassword());
        return b ? new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/vehicle/add")
    public ResponseEntity<HttpStatus> addVehicle(@Valid @RequestBody Vehicle vehicle){

        Vehicle vehicle1 = adminService.addVehicle(vehicle);
        if(vehicle1 != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/admin/vehicle/{vehicleId}")
    public ResponseEntity<HttpStatus> updateVehicle(@PathVariable Integer vehicleId, @Valid @RequestBody Vehicle vehicle){

        Vehicle vehicle1 = adminService.updateVehicle(vehicleId,vehicle);

        if(vehicle1 != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/vehicle/{vehicleId}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable Integer vehicleId){

        Boolean b = adminService.deleteVehicle(vehicleId);

        return b ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/admin/vehicle/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){

        List<Vehicle> vehicleList = adminService.getAllVehicle();

        return !CollectionUtils.isEmpty(vehicleList) ? new ResponseEntity<>(vehicleList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("admin/booking/booking_details")
    public ResponseEntity<List<BookingDetail>> getAllBookingDetails(){

        List<BookingDetail> bookingDetailList =  adminService.getAllBookingDetails();

        return !CollectionUtils.isEmpty(bookingDetailList) ? new ResponseEntity<>(bookingDetailList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("admin/users")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> userList =  adminService.getAllUser();

        return !CollectionUtils.isEmpty(userList) ? new ResponseEntity<>(userList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/admin/vendor/add")
    public ResponseEntity<HttpStatus> addVendor(@Valid @RequestBody Vendor vendor){

        Vendor vendor1 =  adminService.addVendor(vendor);

        if(vendor1 != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/admin/vendor/{vendorId}")
    public ResponseEntity<HttpStatus> updateVendor(@PathVariable Integer vendorId, @Valid @RequestBody Vendor vendor){

        Vendor vendor1 =  adminService.updateVendor(vendorId, vendor);

        if(vendor1 != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/admin/vendor/{vendorId}")
    public ResponseEntity<HttpStatus> deleteVendor(@PathVariable Integer vendorId){

        Boolean b = adminService.deleteVendor(vendorId);

        return b ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("admin/vendors")
    public ResponseEntity<List<Vendor>> getAllvendor(){

        List<Vendor> vendorList =  adminService.getAllVendor();

        return !CollectionUtils.isEmpty(vendorList) ? new ResponseEntity<>(vendorList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> noSuchElementException(NoSuchElementException ee){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ee.getMessage());
        return errors;
    }

    @GetMapping("/admin/feedback")
    public ResponseEntity<List<Feedback>> getAllFeedback(){

        List<Feedback> feedbackList =  adminService.getAllFeedback();

        return !CollectionUtils.isEmpty(feedbackList) ? new ResponseEntity<>(feedbackList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/admin/vendor/{vendorId}")
    public ResponseEntity<Vendor> getVendor(@PathVariable Integer vendorId){
        Vendor vendor = adminService.getVendor(vendorId);
        if(vendor != null){
            return new ResponseEntity<>(vendor,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
