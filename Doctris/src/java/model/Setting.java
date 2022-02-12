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
public class Setting {
    private int setting_id;
    private String setting_name;
    private int id;
    private String name;
    private boolean status;
    private String note;
    private int order;

    public Setting() {
    }

    public Setting(int setting_id, String setting_name) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
    }
    
    public Setting(int id, String name, int setting_id, boolean status) {
        this.id = id;
        this.name = name;
        this.setting_id = setting_id;
        this.status = status;
    }

    public Setting(int id, String name, int setting_id, boolean status, String note, int order) {
        this.id = id;
        this.name = name;
        this.setting_id = setting_id;
        this.status = status;
        this.note = note;
        this.order = order;
    }
    
    public Setting(String name) {
        this.name = name;
    }
    
    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
}
