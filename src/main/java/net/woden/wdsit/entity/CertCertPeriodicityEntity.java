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
public class CertCertPeriodicityEntity {
    
    private int id;
    private int certificateId;
    private int periodicityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public int getPeriodicityId() {
        return periodicityId;
    }

    public void setPeriodicityId(int periodicityId) {
        this.periodicityId = periodicityId;
    }
    
    
    
}
