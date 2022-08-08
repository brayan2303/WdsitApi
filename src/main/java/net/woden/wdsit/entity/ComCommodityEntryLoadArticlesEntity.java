/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

/**
 *
 * @author m.pulido
 */
public class ComCommodityEntryLoadArticlesEntity {
    
    private int id;
    private int idCommodityEntry;
    private String idCommodityEntryNumber;
    private String orders;
    private String transact;
    private String codeAccesory;
    private String nameAccesory;
    private int quantity;
    private String recolectionDate;
    private String assignDate;
    private int userId;
    private String userName;
    private String creationDate;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommodityEntry() {
        return idCommodityEntry;
    }

    public void setIdCommodityEntry(int idCommodityEntry) {
        this.idCommodityEntry = idCommodityEntry;
    }

    public String getIdCommodityEntryNumber() {
        return idCommodityEntryNumber;
    }

    public void setIdCommodityEntryNumber(String idCommodityEntryNumber) {
        this.idCommodityEntryNumber = idCommodityEntryNumber;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getTransact() {
        return transact;
    }

    public void setTransact(String transact) {
        this.transact = transact;
    }

    public String getCodeAccesory() {
        return codeAccesory;
    }

    public void setCodeAccesory(String codeAccesory) {
        this.codeAccesory = codeAccesory;
    }

    public String getNameAccesory() {
        return nameAccesory;
    }

    public void setNameAccesory(String nameAccesory) {
        this.nameAccesory = nameAccesory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRecolectionDate() {
        return recolectionDate;
    }

    public void setRecolectionDate(String recolectionDate) {
        this.recolectionDate = recolectionDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        
}
