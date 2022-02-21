/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Khuong Hung
 */
public class Reservation {
    private int id;
    private Patient patient;
    private Service service;
    private Date date;
    private Time time;
    private String status;
    private Account staff;
    private String description;

    public Reservation() {
    }

    public Reservation(int id, Patient patient, Service service, Date date, Time time, String status, Account staff, String description) {
        this.id = id;
        this.patient = patient;
        this.service = service;
        this.date = date;
        this.time = time;
        this.status = status;
        this.staff = staff;
        this.description = description;
    }
    
    public Reservation(int id, Patient patient, Service service, Date date, Time time, String status) {
        this.id = id;
        this.patient = patient;
        this.service = service;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getStaff() {
        return staff;
    }

    public void setStaff(Account staff) {
        this.staff = staff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
