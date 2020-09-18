package com.demo.vehicle.controller;

import com.demo.vehicle.entity.User;
import com.demo.vehicle.entity.Vehicle;
import com.demo.vehicle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public String createNewUser(@Validated @RequestBody User user){

        String message = userService.validateUser(user.getUsername());
        if(message.equals("username not found")) {
            userService.newUser(user);
            return "Registered Successfully";
        }
        else{
            return "Registeration failed";
        }
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
         return userService.findAll();
    }

    @GetMapping("/users/login")
    public String userLogin(@RequestParam String username, String password){
        String message =   userService.validate(username,password);
        return message;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> noSuchElementException(NoSuchElementException ee){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ee.getMessage());
        return errors;
    }

    @GetMapping("/users/{username}/vehicles")
    public List<Vehicle> findAllVehicles(@PathVariable String username){
        String message = userService.validateUser(username);
        if(message.equals("username found")) {
            return userService.findAllVehicles();
        }
        else{
            throw new NoSuchElementException("username Not Found");
        }
    }

}
