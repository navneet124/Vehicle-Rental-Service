package com.vehicle.payrent.admin.entity;

public class AdminLogin {

    public String adminid;
    public String password;

    public AdminLogin(String adminId, String password) {
        this.adminid = adminid;
        this.password = password;
    }

    public AdminLogin() {
    }

    public String getAdminId() {
        return adminid;
    }

    public void setAdminId(String adminId) {
        this.adminid = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
