/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

/**
 *
 * @author b.algecira
 */
public class ProMeasurementDetailFileModel {
    
    private int measurementDetailId;
    private String name;
    private byte[]file;
    private String type;

    public int getMeasurementDetailId() {
        return measurementDetailId;
    }

    public void setMeasurementDetailId(int measurementDetailId) {
        this.measurementDetailId = measurementDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
