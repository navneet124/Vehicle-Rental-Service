package com.vehicle.payrent.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vehicleId;

    @Column(nullable = false)
    private String vehicleName;

    @Column(nullable = false)
    private boolean isBooked;

    @Column(nullable = false)
    private Integer rentPerday;

}
