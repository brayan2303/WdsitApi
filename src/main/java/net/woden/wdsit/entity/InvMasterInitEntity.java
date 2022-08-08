/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class InvMasterInitEntity {
    
    private int id;
    private String pallet;
    private String codigoSap;
    private String location;
    private String typology;
    private String status;
    private boolean active;
    private String creationDate;
    private int userIdCreation;
    private String updateDate;
    private int userIdUpdate;
    private String userCreation;
    private String userUpdate ;
    private int userAuthorization;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(String codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserIdCreation() {
        return userIdCreation;
    }

    public void setUserIdCreation(int userIdCreation) {
        this.userIdCreation = userIdCreation;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserIdUpdate() {
        return userIdUpdate;
    }

    public void setUserIdUpdate(int userIdUpdate) {
        this.userIdUpdate = userIdUpdate;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public int getUserAuthorization() {
        return userAuthorization;
    }

    public void setUserAuthorization(int userAuthorization) {
        this.userAuthorization = userAuthorization;
    }
}
