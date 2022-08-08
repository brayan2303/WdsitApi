/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

/**
 *
 * @author m.pulido
 */
public class ScpAuditCrossingModel {
    
    private String serial;
    private String estado;
    private String motivoScrap;
    private String pallet;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoScrap() {
        return motivoScrap;
    }

    public void setMotivoScrap(String motivoScrap) {
        this.motivoScrap = motivoScrap;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }
    
}
