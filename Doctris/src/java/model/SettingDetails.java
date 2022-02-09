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
public class SettingDetails {
    private int id;
    private String name;
    private int setting_id;
    private boolean status;
    private String note;

    public SettingDetails() {
    }

    public SettingDetails(int id, String name, int setting_id, boolean status) {
        this.id = id;
        this.name = name;
        this.setting_id = setting_id;
        this.status = status;
    }

    public SettingDetails(int id, String name, int setting_id, boolean status, String note) {
        this.id = id;
        this.name = name;
        this.setting_id = setting_id;
        this.status = status;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }  
}
