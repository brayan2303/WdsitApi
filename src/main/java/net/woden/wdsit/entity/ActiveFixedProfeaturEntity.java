/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.io.Serializable;

/**
 *
 * @author b.algecira
 */
public class ActiveFixedProfeaturEntity implements Serializable {
    
    private int id;
    private int productId;
    private int featuresId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getFeaturesId() {
        return featuresId;
    }

    public void setFeaturesId(int featuresId) {
        this.featuresId = featuresId;
    }

    
}
