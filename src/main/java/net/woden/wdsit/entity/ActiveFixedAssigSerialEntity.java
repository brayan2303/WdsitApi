/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class ActiveFixedAssigSerialEntity {
    
    private int id;
    private int assigmentId;
    private int productfeaturId;
    private String serial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssigmentId() {
        return assigmentId;
    }

    public void setAssigmentId(int assigmentId) {
        this.assigmentId = assigmentId;
    }

    public int getProductfeaturId() {
        return productfeaturId;
    }

    public void setProductfeaturId(int productfeaturId) {
        this.productfeaturId = productfeaturId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
}
