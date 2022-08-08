/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.io.Serializable;

/**
 *
 * @author b.algecira
 */
public class LoadClientPrealertEntity implements Serializable{
    
  private String customer;
  private String CLIENTE;
  private String SERIAL_EQUIPO;
  private String CODIGO_SAP;
  private String ESTADO;
  private String TIPOLOGIA;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getSERIAL_EQUIPO() {
        return SERIAL_EQUIPO;
    }

    public void setSERIAL_EQUIPO(String SERIAL_EQUIPO) {
        this.SERIAL_EQUIPO = SERIAL_EQUIPO;
    }

    public String getCODIGO_SAP() {
        return CODIGO_SAP;
    }

    public void setCODIGO_SAP(String CODIGO_SAP) {
        this.CODIGO_SAP = CODIGO_SAP;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getTIPOLOGIA() {
        return TIPOLOGIA;
    }

    public void setTIPOLOGIA(String TIPOLOGIA) {
        this.TIPOLOGIA = TIPOLOGIA;
    }
    
}
