package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PqrTracingEntity implements Serializable {

    @Id
    private int id;
    private String pqrNumber;
    private String pqrTicket;
    private int pqrTypeId;
    private String pqrType;
    private String type;
    private int eventStatusId;
    private String eventStatus;
    private String observations;
    private String date;
    private int userId;
    private String user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPqrNumber() {
        return pqrNumber;
    }

    public void setPqrNumber(String pqrNumber) {
        this.pqrNumber = pqrNumber;
    }

    public String getPqrTicket() {
        return pqrTicket;
    }

    public void setPqrTicket(String pqrTicket) {
        this.pqrTicket = pqrTicket;
    }

   

    public int getPqrTypeId() {
        return pqrTypeId;
    }

    public void setPqrTypeId(int pqrTypeId) {
        this.pqrTypeId = pqrTypeId;
    }

    public String getPqrType() {
        return pqrType;
    }

    public void setPqrType(String pqrType) {
        this.pqrType = pqrType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(int eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
