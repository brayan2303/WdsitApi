/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class ScpAuditSerialEntity {
    
    private int Id;
    private int palletId;
    private String serial;
    private String mac;
    private String sapCode;
    private String description;
    private String technical;
    private String repairDate;
    private String scrapMotif;
    private String state;
    private String stateMotif;
    private int userId;
    private Boolean active;
    private String creationDate;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPalletId() {
        return palletId;
    }

    public void setPalletId(int palletId) {
        this.palletId = palletId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getScrapMotif() {
        return scrapMotif;
    }

    public void setScrapMotif(String scrapMotif) {
        this.scrapMotif = scrapMotif;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateMotif() {
        return stateMotif;
    }

    public void setStateMotif(String stateMotif) {
        this.stateMotif = stateMotif;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    
    
}
