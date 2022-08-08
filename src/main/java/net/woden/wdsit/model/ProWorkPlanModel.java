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
public class ProWorkPlanModel {
    
    private int id;
    private String name;
    private int activities;
    private int advances;

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

    public int getActivities() {
        return activities;
    }

    public void setActivities(int activities) {
        this.activities = activities;
    }

    public int getAdvances() {
        return advances;
    }

    public void setAdvances(int advances) {
        this.advances = advances;
    }
    
    
    
}
