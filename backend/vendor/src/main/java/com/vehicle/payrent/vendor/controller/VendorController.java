package com.vehicle.payrent.vendor.controller;

import com.vehicle.payrent.vendor.entity.BookingDetail;
import com.vehicle.payrent.vendor.entity.Vehicle;
import com.vehicle.payrent.vendor.entity.VendorLogin;
import com.vehicle.payrent.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/vendor/login")
    public ResponseEntity<HttpStatus> vendorLogin(@RequestBody VendorLogin vendorlogin){

        Boolean b =   vendorService.validate(vendorlogin.getUsername(), vendorlogin.getPassword());
        return b ? new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/vendor/vehicle/{vehicleId}")
    public ResponseEntity<HttpStatus> updateVehicle(@PathVariable Integer vehicleId, @Valid @RequestBody Vehicle vehicle){

        Vehicle vehicle1 = vendorService.updateVehicle(vehicleId,vehicle);
        if(vehicle1 != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/vendor/vehicle/{vehicleId}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable Integer vehicleId){

        Boolean b = vendorService.deleteVehicle(vehicleId);

        return b ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("vendor/booking/booking_details")
    public ResponseEntity<List<BookingDetail>> getAllBookingDetails(){

        List<BookingDetail> bookingDetailList = vendorService.getAllBookingDetails();

        return !CollectionUtils.isEmpty(bookingDetailList) ? new ResponseEntity<>(bookingDetailList,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> noSuchElementException(NoSuchElementException ee){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ee.getMessage());
        return errors;
    }

    @GetMapping("vendor/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicleDetails(){

        List<Vehicle> vehicleList = vendorService.getAllVehicleDetails();
        return !CollectionUtils.isEmpty(vehicleList) ? new ResponseEntity<>(vehicleList,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("vendor/vehicle/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Integer vehicleId){

        Vehicle vehicle = vendorService.getVehicle(vehicleId);
        if(vehicle != null){
            return new ResponseEntity<>(vehicle,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
