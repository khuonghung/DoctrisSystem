/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Khuong Hung
 */
public class Doctor {
    Setting setting;
    private int doctor_id;
    private int role_id;
    private String doctor_name;
    Account account;
    private boolean gender;
    private Date DOB;
    private int phone;
    private String description;
    private boolean status;
    private String img;

    public Doctor() {
    }

    public Doctor(Setting setting, int doctor_id, int role_id, String doctor_name, Account account, boolean gender, Date DOB, int phone, String description, boolean status, String img) {
        this.setting = setting;
        this.doctor_id = doctor_id;
        this.role_id = role_id;
        this.doctor_name = doctor_name;
        this.account = account;
        this.gender = gender;
        this.DOB = DOB;
        this.phone = phone;
        this.description = description;
        this.status = status;
        this.img = img;
    }
    
    public Doctor(Setting setting, int doctor_id, String doctor_name, boolean gender, boolean status) {
        this.setting = setting;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.gender = gender;
        this.status = status;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSettingdetails(Setting settingdetails) {
        this.setting = setting;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

       
    
}
