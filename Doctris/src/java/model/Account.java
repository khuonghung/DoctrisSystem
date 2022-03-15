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
public class Account {

    private String username;
    Role role;
    private String password;
    private String name;
    private boolean gender;
    private int phone;
    private String email;
    private String img;
    private boolean status;
    private String captcha;

    public Account() {
    }

    public Account(String username, Role role, String password, String name, boolean gender, int phone, String email, String img, boolean status) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.img = img;
        this.status = status;
    }

    public Account(String username, Role role, String name, boolean gender, int phone, String email, String img, boolean status) {
        this.username = username;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.img = img;
        this.status = status;
    }

    public Account(String username, Role role, String name, boolean gender, int phone, String email, boolean status) {
        this.username = username;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public Account(String img, String username, String name, String email, boolean gender, int phone) {
        this.img = img;
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    public Account(String username, String name, boolean gender) {
        this.username = username;
        this.name = name;
        this.gender = gender;
    }

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String email, String captcha, String img) {
        if (username != null) {
            this.username = username;
        }
        if (email != null) {
            this.email = email;
        }
        if (captcha != null) {
            this.captcha = captcha;
        }
        if (img != null) {
            this.img = img;
        }
    }

    public Account(String img, String name, int phone, boolean gender, String email) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        if (img != null) {
            this.img = img;
        }
        if (email != null) {
            this.email = email;
        }
    }

    public Account(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
