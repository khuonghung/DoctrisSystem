/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Khuong Hung
 */
public class RateStar {
    private int star;
    private int countfeedback;

    public RateStar() {
    }

    public RateStar(int star, int countfeedback) {
        this.star = star;
        this.countfeedback = countfeedback;
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
    
    
}
