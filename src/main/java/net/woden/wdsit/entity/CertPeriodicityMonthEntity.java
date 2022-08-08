/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

/**
 *
 * @author m.pulido
 */
public class CertPeriodicityMonthEntity {
    
    private int id;
    private int periodicityId;
    private int monthId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodicityId() {
        return periodicityId;
    }

    public void setPeriodicityId(int periodicityId) {
        this.periodicityId = periodicityId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
    
    
}
