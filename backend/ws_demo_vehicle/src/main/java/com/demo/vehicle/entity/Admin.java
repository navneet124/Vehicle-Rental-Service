package com.demo.vehicle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @Column(unique = true)
    @NotBlank(message = "username should not be blank")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "password should not be blank")
    private String password;
}
