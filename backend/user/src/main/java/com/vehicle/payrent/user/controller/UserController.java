package com.vehicle.payrent.user.controller;

import com.vehicle.payrent.user.entity.*;
import com.vehicle.payrent.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    UserLogin userlogin = new UserLogin();

    @PostMapping("/users/register")
    public ResponseEntity<HttpStatus> createNewUser(@RequestBody User user){

        log.info(String.valueOf(user));
        String message = userService.validateUser(user.getUsername());
        if(message.equals("username not found")) {
            userService.newUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> userList= userService.findAll();

        return !CollectionUtils.isEmpty(userList) ?
                new ResponseEntity<>(userList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/login")
    public ResponseEntity<HttpStatus> userlogin(@RequestBody UserLogin userlogin){

        Boolean b =   userService.validate(userlogin.getUsername(),userlogin.getPassword());
        return b ? new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> noSuchElementException(NoSuchElementException ee){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ee.getMessage());
        return errors;
    }

    @GetMapping("/users/{username}/vehicles")
    public ResponseEntity<List<Vehicle>> findAllVehicles(@PathVariable String username){
        String message = userService.validateUser(username);
        if(message.equals("username found")) {
            List<Vehicle> vehicleList = userService.findAllVehicles();
            return new ResponseEntity<>(vehicleList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/users/{username}/vehicles/book")
    //public ResponseEntity<BookingDetail> isBooked(@RequestParam("username") String username, @RequestParam("vehicleId") Integer vehicleId, @RequestParam Integer noOfDays){
      public ResponseEntity<BookingDetail> isBooked(@PathVariable String username, @RequestBody BookingDetail bookingDetail){

            String message = userService.validateUser(username);
        if(message.equals("username found")) {
            BookingDetail bookingDetail1 = userService.isBooked(username,bookingDetail.getVehicleId(),bookingDetail.getNoOfDays());
            return new ResponseEntity<>(bookingDetail1,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{bookingId}/{totalAmount}")
    public ResponseEntity<HttpStatus> payRent(@PathVariable Integer bookingId, @PathVariable Integer totalAmount){
        Boolean b = userService.payRent(bookingId,totalAmount);

        return b ? new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users/feedback")
    public ResponseEntity<HttpStatus> feedback(@RequestBody Feedback feedback){
        Boolean b = userService.newFeedback(feedback);

        return b ? new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
