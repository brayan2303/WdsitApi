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
public class LoadClientPersonCrossingModel {

    private String cliente;
    private String serial_equipo;
    private String codigo_sap;
    private String estado;
    private String tipologia;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSerial_equipo() {
        return serial_equipo;
    }

    public void setSerial_equipo(String serial_equipo) {
        this.serial_equipo = serial_equipo;
    }

    public String getCodigo_sap() {
        return codigo_sap;
    }

    public void setCodigo_sap(String codigo_sap) {
        this.codigo_sap = codigo_sap;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
