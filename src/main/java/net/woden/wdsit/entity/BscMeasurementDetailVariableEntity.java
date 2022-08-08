package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BscMeasurementDetailVariableEntity implements Serializable {
    @Id
    private int id;
    private int measurementDetailId;
    private int variableId;
    private String variable;
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeasurementDetailId() {
        return measurementDetailId;
    }

    public void setMeasurementDetailId(int measurementDetailId) {
        this.measurementDetailId = measurementDetailId;
    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }
    
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
    
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
