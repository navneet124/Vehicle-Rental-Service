package com.demo.vehicle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vehicleId;

    @Column(nullable = false)
    @NotBlank(message = "vehicle name should not be blank")
    private String vehicleName;

    @Column(nullable = false)
    @NotBlank(message = "is booked should not be blank")
    private boolean isBooked;

    @Column(nullable = false)
    @NotBlank(message = "rent per day should not be blank")
    private Integer rentPerday;
}
