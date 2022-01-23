/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Trung
 */
public class Patient {

    
    private int patient_id;
    private String username;
    private int role_id;
    private boolean status;
    private String address;
    private Date DOB;
    Account account;

    public Patient() {
    }

    public Patient(int patient_id, String username, int role_id, boolean status, String address, Date DOB, Account account) {
        this.patient_id = patient_id;
        this.username = username;
        this.role_id = role_id;
        this.status = status;
        this.address = address;
        this.DOB = DOB;
        this.account = account;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    

}
