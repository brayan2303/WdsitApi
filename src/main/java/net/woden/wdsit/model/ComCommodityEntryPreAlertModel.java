/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

/**
 *
 * @author m.pulido
 */
public class ComCommodityEntryPreAlertModel {
    
    private String orders;
    private String transact;
    private String codeAccessory;
    private String nameAccessory;
    private int quantity;
    private String assignDate;
    private String recolectionDate;

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

    public String getCodeAccessory() {
        return codeAccessory;
    }

    public void setCodeAccessory(String codeAccessory) {
        this.codeAccessory = codeAccessory;
    }

    public String getNameAccessory() {
        return nameAccessory;
    }

    public void setNameAccessory(String nameAccessory) {
        this.nameAccessory = nameAccessory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getRecolectionDate() {
        return recolectionDate;
    }

    public void setRecolectionDate(String recolectionDate) {
        this.recolectionDate = recolectionDate;
    }
        
}
