package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BscActivityEntity implements Serializable {
    @Id
    private int id;
    private String name;
    private String description;
    private double percentage;
    private String deliverables;
    private String deliverDate;
    private int year;
    private int perspectiveId;
    private String perspective;
    private int strategicObjetiveId;
    private String strategicObjetive;
    private int workPlanId;
    private String workPlan;
    private int responsibleUserId;
    private String responsibleUser;
    private int creationUserId;
    private String creationUser;
    private int statusId;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerspectiveId() {
        return perspectiveId;
    }

    public void setPerspectiveId(int perspectiveId) {
        this.perspectiveId = perspectiveId;
    }

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

    public int getStrategicObjetiveId() {
        return strategicObjetiveId;
    }

    public void setStrategicObjetiveId(int strategicObjetiveId) {
        this.strategicObjetiveId = strategicObjetiveId;
    }

    public String getStrategicObjetive() {
        return strategicObjetive;
    }

    public void setStrategicObjetive(String strategicObjetive) {
        this.strategicObjetive = strategicObjetive;
    }

    public int getWorkPlanId() {
        return workPlanId;
    }

    public void setWorkPlanId(int workPlanId) {
        this.workPlanId = workPlanId;
    }

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }

    public int getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(int responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public String getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(String responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
