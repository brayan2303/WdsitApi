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
public class ProWorkPlanEntity {
    
    private int id;
    private String name;
    private int year;
    private int strategicObjetiveId;
    private String strategicObjetive;
    private int perspectiveId;    
    private String perspective;
    private int creationUserId;
    private String creationUser;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
