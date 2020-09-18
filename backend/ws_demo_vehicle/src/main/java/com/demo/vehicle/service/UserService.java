package com.demo.vehicle.service;

import com.demo.vehicle.entity.User;
import com.demo.vehicle.entity.Vehicle;
import com.demo.vehicle.repository.UserRepository;
import com.demo.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public String validate(String username,String password){
        User registration=userRepository.findByUserName(username,password);
        if(registration !=null){
            return "login successfully";
        }
        //throw new NoSuchElementException("Username not found");
        return "login Failed";
    }

    public String validateUser(String username){
        User registration=userRepository.findByUser(username);
        if(registration !=null){
            return "username found";
            //throw new NoSuchElementException("username found");
        }else {
            return "username not found";
        }
    }

    public User newUser( User userRegister){
        return userRepository.save(userRegister);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<Vehicle> findAllVehicles(){
        return vehicleRepository.findAllVehicles();
    }
}
