/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Khuong Hung
 */
public class RateStar {

    private int id;
    private String feedback;
    private int star;
    private Timestamp date;
    private int countfeedback;
    private Account user;

    public RateStar() {
    }

    public RateStar(Account user, int star, String feedback, Timestamp date) {
        this.feedback = feedback;
        this.star = star;
        this.date = date;
        this.user = user;
    }

    public RateStar(int star, int countfeedback) {
        this.star = star;
        this.countfeedback = countfeedback;
    }
    
    public RateStar(Account user, int star, String feedback) {
        this.user = user;
        this.star = star;
        this.feedback = feedback;
    }
    
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCountfeedback() {
        return countfeedback;
    }

    public void setCountfeedback(int countfeedback) {
        this.countfeedback = countfeedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

}
