
package net.woden.wdsit.entity;

import java.io.Serializable;
import java.sql.Date;

public class CovFormDayEntity implements Serializable{
    private int id;
    private Date creationDate;
    private int IdentificationUser;
    private String creationUser;
    private String identificationUserCar;
    private String hasSymptoms;
    private Date symptomsDate;
    private String descripSymptims;
    private String typeDocument;
    private String name;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getHasSymptoms() {
        return hasSymptoms;
    }

    public void setHasSymptoms(String hasSymptoms) {
        this.hasSymptoms = hasSymptoms;
    }

    public Date getSymptomsDate() {
        return symptomsDate;
    }

    public void setSymptomsDate(Date symptomsDate) {
        this.symptomsDate = symptomsDate;
    }

    public String getDescripSymptims() {
        return descripSymptims;
    }

    public void setDescripSymptims(String descripSymptims) {
        this.descripSymptims = descripSymptims;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getIdentificationUser() {
        return IdentificationUser;
    }

    public void setIdentificationUser(int IdentificationUser) {
        this.IdentificationUser = IdentificationUser;
    }

    public String getIdentificationUserCar() {
        return identificationUserCar;
    }

    public void setIdentificationUserCar(String identificationUserCar) {
        this.identificationUserCar = identificationUserCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
  
    
}
