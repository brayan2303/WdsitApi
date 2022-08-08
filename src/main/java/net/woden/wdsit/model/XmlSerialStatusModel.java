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
import java.io.Serializable;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;

//@XmlRootElement(name = "xmlSerialStatusModel")
public class XmlSerialStatusModel implements Serializable{
    
    private int id;
    private String serial;
    private String estado;
    private String customer;
    private String xml[][];
    private String as[][];
    private String gfxml[][];
   

    public int getId() {
        return id;
    }
  //  @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }
 //   @XmlElement
    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getEstado() {
        return estado;
    }
   // @XmlElement
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCustomer() {
        return customer;
    }
 //   @XmlElement
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String[][] getXml() {
        return xml;
    }
   // @XmlElement
    public void setXml(String[][] xml) {
        this.xml = xml;
    }

    public String[][] getAs() {
        return as;
    }
  //  @XmlElement
    public void setAs(String[][] as) {
        this.as = as;
    }

    public String[][] getGfxml() {
        return gfxml;
    }
 //    @XmlElement
    public void setGfxml(String[][] gfxml) {
        this.gfxml = gfxml;
    }
    
}