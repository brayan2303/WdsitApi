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
public class ScpAuditPreviousSerialEntity {
    
    private int id;
    private int auditPreviousId;
    private String auditPreviousName;
    private String serial;
    private String codigoSap;
    private String descripcion;
    private String motivoScrap;
    private String estado;
    private int userId;
    private String userName;
    private String creationDate;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuditPreviousId() {
        return auditPreviousId;
    }

    public void setAuditPreviousId(int auditPreviousId) {
        this.auditPreviousId = auditPreviousId;
    }

    public String getAuditPreviousName() {
        return auditPreviousName;
    }

    public void setAuditPreviousName(String auditPreviousName) {
        this.auditPreviousName = auditPreviousName;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(String codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivoScrap() {
        return motivoScrap;
    }

    public void setMotivoScrap(String motivoScrap) {
        this.motivoScrap = motivoScrap;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
    
}
