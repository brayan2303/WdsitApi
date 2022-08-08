package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TasTaskEntity implements Serializable {
    @Id
    private int id;
    private String title;
    private String priority;
    private String status;
    private String type;
    private String startDate;
    private String endDate;
    private int requestPersonId;
    private String requestPerson;
    private int assignedPersonId;
    private String assignedPerson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getRequestPersonId() {
        return requestPersonId;
    }

    public void setRequestPersonId(int requestPersonId) {
        this.requestPersonId = requestPersonId;
    }

    public String getRequestPerson() {
        return requestPerson;
    }

    public void setRequestPerson(String requestPerson) {
        this.requestPerson = requestPerson;
    }

    public int getAssignedPersonId() {
        return assignedPersonId;
    }

    public void setAssignedPersonId(int assignedPersonId) {
        this.assignedPersonId = assignedPersonId;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }
}
