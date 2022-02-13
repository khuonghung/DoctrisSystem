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
public class Role {
    private int role_id;
    private String name;
    Setting setting;
    private String note;

    public Role() {
    }

    public Role(int role_id, String name, Setting setting) {
        this.role_id = role_id;
        this.name = name;
        this.setting = setting;
    }

    public Role(int role_id, String name, Setting setting, String note) {
        this.role_id = role_id;
        this.name = name;
        this.setting = setting;
        this.note = note;
    }
    
    public Role(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }
    
    public Role(int role_id) {
        this.role_id = role_id;
    }
    
    public Role(String name) {
        this.name = name;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
