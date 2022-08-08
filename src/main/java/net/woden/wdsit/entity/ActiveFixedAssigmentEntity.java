/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author b.algecira
 */
public class ActiveFixedAssigmentEntity implements Serializable{
    
    private int id;
    private int identification;
    private String name;
    private String mail;
    private String costCenter;
    private String position;
    private String city;
    private String productEquip;
    private String product;
    private String serial;
    private String exitPermanent;
    private String statusEquipament;
    private String personRes;
    private Date creationDate;
    private Boolean approvedRejected;
    private String person;
    private String answer;
    private String nameRes;
    private String nameProduct;
    private Boolean active;
    private Boolean exitEntry;
    private String exitDate;
    private String entryDate;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProductEquip() {
        return productEquip;
    }

    public void setProductEquip(String productEquip) {
        this.productEquip = productEquip;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getExitPermanent() {
        return exitPermanent;
    }

    public void setExitPermanent(String exitPermanent) {
        this.exitPermanent = exitPermanent;
    }

  
    public String getStatusEquipament() {
        return statusEquipament;
    }

    public void setStatusEquipament(String statusEquipament) {
        this.statusEquipament = statusEquipament;
    }

    public String getPersonRes() {
        return personRes;
    }

    public void setPersonRes(String personRes) {
        this.personRes = personRes;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getApprovedRejected() {
        return approvedRejected;
    }

    public void setApprovedRejected(Boolean approvedRejected) {
        this.approvedRejected = approvedRejected;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
   
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getExitEntry() {
        return exitEntry;
    }

    public void setExitEntry(Boolean exitEntry) {
        this.exitEntry = exitEntry;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    
    
}
