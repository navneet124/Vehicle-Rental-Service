package com.vehicle.payrent.user.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vehicle.payrent.user.entity.BookingDetail;
import com.vehicle.payrent.user.entity.Feedback;
import com.vehicle.payrent.user.entity.User;
import com.vehicle.payrent.user.entity.Vehicle;
import com.vehicle.payrent.user.repository.BookingRepository;
import com.vehicle.payrent.user.repository.FeedbackRepository;
import com.vehicle.payrent.user.repository.UserRepository;
import com.vehicle.payrent.user.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Boolean validate(String username,String password){
        User registration=userRepository.findByUserName(username,password);
        if(registration !=null){
            return true;
        }
        //throw new NoSuchElementException("Username not found");
        return false;
    }

    @Transactional
    public String validateUser(String username){
        User registration=userRepository.findByUser(username);
        if(registration !=null){
            return "username found";
            //throw new NoSuchElementException("username found");
        }else {
            return "username not found";
        }
    }

    @Transactional
    public User newUser( User userRegister){
        return userRepository.save(userRegister);
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public List<Vehicle> findAllVehicles(){
        return vehicleRepository.findAllVehicles();
    }

    @Transactional
    public BookingDetail isBooked(String username, Integer vehicleId, Integer noOfDays){

        User user = userRepository.getUserId(username);
        BookingDetail bookingDetail = new BookingDetail();
        Vehicle originalVehicleDetails = vehicleRepository.findById(vehicleId).get();

        if(user != null && originalVehicleDetails != null && !originalVehicleDetails.isBooked()) {
            originalVehicleDetails.setBooked(true);
            bookingDetail.setUsername(username);
            bookingDetail.setVehicleId(vehicleId);
            bookingDetail.setNoOfDays(noOfDays);
            bookingDetail.setTotalAmount(originalVehicleDetails.getRentPerday() * noOfDays);
            vehicleRepository.save(originalVehicleDetails);
            bookingRepository.save(bookingDetail);
            return bookingDetail;
        }
        else
            throw new NoSuchElementException("username or vehicle Id not found");

    }

    @Transactional
    public Boolean payRent(Integer bookingId, Integer totalAmount){

        Boolean b = false;
        BookingDetail bookingDetail = bookingRepository.findById(bookingId).get();
        if(bookingDetail != null) {
            Integer amount = bookingDetail.getTotalAmount();
            if (amount.equals(totalAmount)) {
               b= true;
            }
        }
        else
            b= false;

        return b;
    }

    @Transactional
    public Boolean newFeedback(Feedback feedback){
        Feedback feedback1 = feedbackRepository.save(feedback);
        if(feedback1 != null)
            return true;
        else
            return false;
    }
}
