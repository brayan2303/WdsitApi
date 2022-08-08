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
public class ScrapReportEntity {
      
    private int id;
    private String customer;
    private String dateEntry;
    private String dateAudi;
    private String liderAssurance;
    private String typeAudi;
    private String outcomeAudi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(String dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getDateAudi() {
        return dateAudi;
    }

    public void setDateAudi(String dateAudi) {
        this.dateAudi = dateAudi;
    }

    public String getLiderAssurance() {
        return liderAssurance;
    }

    public void setLiderAssurance(String liderAssurance) {
        this.liderAssurance = liderAssurance;
    }

    public String getTypeAudi() {
        return typeAudi;
    }

    public void setTypeAudi(String typeAudi) {
        this.typeAudi = typeAudi;
    }

    public String getOutcomeAudi() {
        return outcomeAudi;
    }

    public void setOutcomeAudi(String outcomeAudi) {
        this.outcomeAudi = outcomeAudi;
    }
    
}
