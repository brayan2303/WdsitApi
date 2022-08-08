package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GenCenterCostEntity implements Serializable {
    @Id
    private int id;
    private String code;
    private String description;
    private int segmentId;
    private boolean incomeActive;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }
    
    public boolean isIncomeActive() {
        return incomeActive;
    }

    public void setIncomeActive(boolean incomeActive) {
        this.incomeActive = incomeActive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
