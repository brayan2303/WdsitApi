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
public class ProPerspectiveModel {
    
    private int id;
    private String name;
    private int strategicObjetives;
    private int indicators;

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

    public int getStrategicObjetives() {
        return strategicObjetives;
    }

    public void setStrategicObjetives(int strategicObjetives) {
        this.strategicObjetives = strategicObjetives;
    }

    public int getIndicators() {
        return indicators;
    }

    public void setIndicators(int indicators) {
        this.indicators = indicators;
    }
    
    
}
