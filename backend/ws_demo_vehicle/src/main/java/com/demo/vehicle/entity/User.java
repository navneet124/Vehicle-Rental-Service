package com.demo.vehicle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true)
    @NotBlank(message = "username should not be blank")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "firstname should not be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "lastname should not be blank")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "password should not be blank")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "address should not be blank")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "gender should not be blank")
    private String gender;

    @Column(nullable = false)
    private Integer Phone;

}
