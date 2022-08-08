/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.model;

/**
 *
 * @author b.algecira
 */
public class ScpAuditSerialModel {
    
    private int id;
    private String codeAudit;
    private int auditPreviousId;
    private String auditPreviousName;
    private String state;
    private int stateMotifId;
    private String stateMotifName;
    private Boolean openPallet;
    private String typeAudit;
    private String typeAuditName;
    private int levelRuleId;
    private String levelRuleName;
    private int levelRuleQuantityId;
    private int levelRuleQuantity;
    private int userId;
    private String userName;
    private String creationDate;
    private Boolean active;
    private int noveltyAccepted;
    private int noveltyRejected;
    private int reject; 
    private String approvedRejected;

    public String getApprovedRejected() {
        return approvedRejected;
    }

    public void setApprovedRejected(String approvedRejected) {
        this.approvedRejected = approvedRejected;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeAudit() {
        return codeAudit;
    }

    public void setCodeAudit(String codeAudit) {
        this.codeAudit = codeAudit;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateMotifName() {
        return stateMotifName;
    }

    public void setStateMotifName(String stateMotifName) {
        this.stateMotifName = stateMotifName;
    }

    public String getTypeAudit() {
        return typeAudit;
    }

    public void setTypeAudit(String typeAudit) {
        this.typeAudit = typeAudit;
    }

    public String getTypeAuditName() {
        return typeAuditName;
    }

    public void setTypeAuditName(String typeAuditName) {
        this.typeAuditName = typeAuditName;
    }

    public int getLevelRuleId() {
        return levelRuleId;
    }

    public void setLevelRuleId(int levelRuleId) {
        this.levelRuleId = levelRuleId;
    }

    public String getLevelRuleName() {
        return levelRuleName;
    }

    public void setLevelRuleName(String levelRuleName) {
        this.levelRuleName = levelRuleName;
    }

    public int getLevelRuleQuantityId() {
        return levelRuleQuantityId;
    }

    public void setLevelRuleQuantityId(int levelRuleQuantityId) {
        this.levelRuleQuantityId = levelRuleQuantityId;
    }

    public int getLevelRuleQuantity() {
        return levelRuleQuantity;
    }

    public void setLevelRuleQuantity(int levelRuleQuantity) {
        this.levelRuleQuantity = levelRuleQuantity;
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

    public int getStateMotifId() {
        return stateMotifId;
    }

    public void setStateMotifId(int stateMotifId) {
        this.stateMotifId = stateMotifId;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getOpenPallet() {
        return openPallet;
    }

    public void setOpenPallet(Boolean openPallet) {
        this.openPallet = openPallet;
    }

    public int getNoveltyAccepted() {
        return noveltyAccepted;
    }

    public void setNoveltyAccepted(int noveltyAccepted) {
        this.noveltyAccepted = noveltyAccepted;
    }

    public int getNoveltyRejected() {
        return noveltyRejected;
    }

    public void setNoveltyRejected(int noveltyRejected) {
        this.noveltyRejected = noveltyRejected;
    }

    public int getReject() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject = reject;
    }
      
}
