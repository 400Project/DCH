package com.oyatech.dch.model;

import com.oyatech.dch.util.Utils;

import org.jetbrains.annotations.NotNull;

public class Staff {
    public  String staffId;
  private   String firstName;
    private String otherName;
    private String address;
    private String dob;
    private String gender;
    private String department;
    private String staffRole;
    private String email;
    private String mobile;

    private String nhis;
    private String role;
    private  String dateEmployed;

    public String getStaffId() {
        return staffId;
    }


    public Staff(String firstName, String otherName,
                 String address, String dob,
                 String gender, String department,String staffRole,
                 String mobile, String email,String insurance){
        this.firstName = firstName;
        this.otherName = otherName;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.staffRole = staffRole;
        this.mobile = mobile;
        this.email = email;
        this.nhis = insurance;
        this.staffId = Utils.INSTANCE.staffID(department);
        this.dateEmployed = Utils.INSTANCE.getDate();
    }
    public Staff(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNhis() {
        return nhis;
    }

    public void setNhis(String nhis) {
        this.nhis = nhis;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getDateEmployed() {
        return dateEmployed;
    }
}
