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
public class ScpAuditLevelRuleQuantityEntity {
    
    private int id;
    private int levelRuleId;
    private String levelRuleName;
    private int quantityMin;
    private int quantityMax;
    private int noveltyAccepted;
    private int noveltyRejected;
    private int show;
    private int userId;
    private String userName;
    private String creationDate;
    private boolean active;
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
    

    public int getQuantityMin() {
        return quantityMin;
    }

    public void setQuantityMin(int quantityMin) {
        this.quantityMin = quantityMin;
    }

    public int getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(int quantityMax) {
        this.quantityMax = quantityMax;
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

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
}
