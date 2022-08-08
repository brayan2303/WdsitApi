/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.sql.Date;

/**
 *
 * @author b.algecira
 */
public class ActiveFixedExitEntity {
    
    private int id; 
    private String identification;
    private String name;
    private String area;
    private String position;
    private String equipment;
    private String serial;
    private String serialEquipment;
    private Date creationDate;
    private int userId;
    private String personExit;
    private String associatedSerial;
    private String observation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSerialEquipment() {
        return serialEquipment;
    }

    public void setSerialEquipment(String serialEquipment) {
        this.serialEquipment = serialEquipment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPersonExit() {
        return personExit;
    }

    public void setPersonExit(String personExit) {
        this.personExit = personExit;
    }

    public String getAssociatedSerial() {
        return associatedSerial;
    }

    public void setAssociatedSerial(String associatedSerial) {
        this.associatedSerial = associatedSerial;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    
    
    
    
    
}
