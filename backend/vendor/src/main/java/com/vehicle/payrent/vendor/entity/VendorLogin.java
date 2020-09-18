package com.vehicle.payrent.vendor.entity;

public class VendorLogin {

    public String username;
    public String password;

    public VendorLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public VendorLogin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
