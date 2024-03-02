/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author THevY
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String address;
    private String addressReiceive;
    private String addressBuying;
    private Date birth;
    private String telNum;
    private String email;
    private int isAdmin;

    public User() {
    }

    public User(String userId, String username, String password, String name, String gender, String address, String addressReiceive, String addressBuying, Date birth, String telNum, String email, int isAdmin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.addressReiceive = addressReiceive;
        this.addressBuying = addressBuying;
        this.birth = birth;
        this.telNum = telNum;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    

    

   

    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressReiceive() {
        return addressReiceive;
    }

    public void setAddressReiceive(String addressReiceive) {
        this.addressReiceive = addressReiceive;
    }

    public String getAddressBuying() {
        return addressBuying;
    }

    public void setAddressBuying(String addressBuying) {
        this.addressBuying = addressBuying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

   

    
   
    

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
