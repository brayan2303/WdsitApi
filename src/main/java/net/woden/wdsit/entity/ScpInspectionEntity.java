/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class ScpInspectionEntity {
    
    private int id;
    private String checkList;
    private String description;
    private String sampling;
    private String qty;
    private String rejectionThreshold;
    private String status;
    private String creationDate;
    private int auditId;
    private int userId;
    private String updateDate;
    private int userIdupdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSampling() {
        return sampling;
    }

    public void setSampling(String sampling) {
        this.sampling = sampling;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRejectionThreshold() {
        return rejectionThreshold;
    }

    public void setRejectionThreshold(String rejectionThreshold) {
        this.rejectionThreshold = rejectionThreshold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserIdupdate() {
        return userIdupdate;
    }

    public void setUserIdupdate(int userIdupdate) {
        this.userIdupdate = userIdupdate;
    }
    
    
    
}
