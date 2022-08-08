package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BscIndicatorEntity implements Serializable {
    @Id
    private int id;
    private String name;
    private double percentage;
    private String direction;
    private int strategicObjetiveId;
    private String strategicObjetive;
    private int perspectiveId;
    private String perspective;
    private int year;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getStrategicObjetiveId() {
        return strategicObjetiveId;
    }

    public void setStrategicObjetiveId(int strategicObjetiveId) {
        this.strategicObjetiveId = strategicObjetiveId;
    }

    public String getStrategicObjetive() {
        return strategicObjetive;
    }

    public void setStrategicObjetive(String strategicObjetive) {
        this.strategicObjetive = strategicObjetive;
    }
    
    public int getPerspectiveId() {
        return perspectiveId;
    }

    public void setPerspectiveId(int perspectiveId) {
        this.perspectiveId = perspectiveId;
    }
    
    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
