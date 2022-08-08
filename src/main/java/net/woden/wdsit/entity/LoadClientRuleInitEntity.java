/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class LoadClientRuleInitEntity {
    
    private int id;
    private String name;
    private String description;
    private int customerId;
    private boolean active;
    private String parameterization;
    private int parameterizationId;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getParameterization() {
        return parameterization;
    }

    public void setParameterization(String parameterization) {
        this.parameterization = parameterization;
    }

    public int getParameterizationId() {
        return parameterizationId;
    }

    public void setParameterizationId(int parameterizationId) {
        this.parameterizationId = parameterizationId;
    }
    
}
