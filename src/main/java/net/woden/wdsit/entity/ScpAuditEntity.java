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
public class ScpAuditEntity {
    
    private int id;
    private int auditPreviousId;
    private String auditPreviousName;
    private String codeAudit;
    private String state;
    private int stateMotifId;
    private String stateMotifName;
    private boolean openPallet;
    private String typeAudit;
    private String typeAuditName;
    private int levelRuleId;
    private String levelRuleName;
    private int levelRuleQuantityId;
    private int levelRuleQuantity;
    private int userId;
    private String userName;
    private String creationDate;
    private String customer;
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

    public String getCodeAudit() {
        return codeAudit;
    }

    public void setCodeAudit(String codeAudit) {
        this.codeAudit = codeAudit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStateMotifId() {
        return stateMotifId;
    }

    public void setStateMotifId(int stateMotifId) {
        this.stateMotifId = stateMotifId;
    }

    public String getStateMotifName() {
        return stateMotifName;
    }

    public void setStateMotifName(String stateMotifName) {
        this.stateMotifName = stateMotifName;
    }

    public boolean isOpenPallet() {
        return openPallet;
    }

    public void setOpenPallet(boolean openPallet) {
        this.openPallet = openPallet;
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

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
