/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

/**
 *
 * @author j.hilarion
 */
public class DryIceModel {
    
    private int id;
    private String serial;
    private String creationDate;
    private boolean active;
    private String description;
    private String codSap;
    private String model;
    private int userId;
    private String creationDatefinish;
    private String userName;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodSap() {
        return codSap;
    }

    public void setCodSap(String codSap) {
        this.codSap = codSap;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreationDatefinish() {
        return creationDatefinish;
    }

    public void setCreationDatefinish(String creationDatefinish) {
        this.creationDatefinish = creationDatefinish;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

   

}
