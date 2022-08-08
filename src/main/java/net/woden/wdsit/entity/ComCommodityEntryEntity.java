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
public class ComCommodityEntryEntity {
    
    private int id;
    private String number;
    private int customerId;
    private String customerName;
    private String city;
    private String origin;
    private String originType;
    private String agentIdentification;
    private String state;
    private boolean automatic;
    private int userId;
    private String userName;
    private String creationDate;
    private boolean active;
    private int approvedUserId;
    private String approvedUserName;
    private String approvedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getAgentIdentification() {
        return agentIdentification;
    }

    public void setAgentIdentification(String agentIdentification) {
        this.agentIdentification = agentIdentification;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
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

    public int getApprovedUserId() {
        return approvedUserId;
    }

    public void setApprovedUserId(int approvedUserId) {
        this.approvedUserId = approvedUserId;
    }

    public String getApprovedUserName() {
        return approvedUserName;
    }

    public void setApprovedUserName(String approvedUserName) {
        this.approvedUserName = approvedUserName;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

            
}
