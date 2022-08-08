/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author b.algecira
 */
public class ActiveFixedProductEntity implements Serializable{
    
    private int id;
    private Date creationDate;
    private String name;
    private String description;
    private String type;
    private String supplier;
    private String supplierAproved;
    private Boolean multipleAssignment;
    private Boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierAproved() {
        return supplierAproved;
    }

    public void setSupplierAproved(String supplierAproved) {
        this.supplierAproved = supplierAproved;
    }
    

    public Boolean getMultipleAssignment() {
        return multipleAssignment;
    }

    public void setMultipleAssignment(Boolean multipleAssignment) {
        this.multipleAssignment = multipleAssignment;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
}
