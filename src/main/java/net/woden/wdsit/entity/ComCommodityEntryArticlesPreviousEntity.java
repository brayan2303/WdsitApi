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
public class ComCommodityEntryArticlesPreviousEntity {
    
    private int id;
    private int idCommodityEntry;
    private String idCommodityEntryName;
    private String sapCode;
    private String sapCodeDescription;
    private int quantity;
    private int userId;
    private String userName;
    private String creationDate;

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

    public String getIdCommodityEntryName() {
        return idCommodityEntryName;
    }

    public void setIdCommodityEntryName(String idCommodityEntryName) {
        this.idCommodityEntryName = idCommodityEntryName;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCodeDescription() {
        return sapCodeDescription;
    }

    public void setSapCodeDescription(String sapCodeDescription) {
        this.sapCodeDescription = sapCodeDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

        
}
