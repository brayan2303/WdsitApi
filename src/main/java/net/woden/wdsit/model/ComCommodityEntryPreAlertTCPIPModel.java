/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

import java.util.ArrayList;

/**
 *
 * @author m.pulido
 */
public class ComCommodityEntryPreAlertTCPIPModel {
    
    private String project;
    private String city;
    private String agentName;
    private String agentIdentification;
    private boolean automatic;
    private int userCreationId;
    private ArrayList<ComCommodityEntryPreAlertModel> accessory;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentIdentification() {
        return agentIdentification;
    }

    public void setAgentIdentification(String agentIdentification) {
        this.agentIdentification = agentIdentification;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public int getUserCreationId() {
        return userCreationId;
    }

    public void setUserCreationId(int userCreationId) {
        this.userCreationId = userCreationId;
    }

    public ArrayList<ComCommodityEntryPreAlertModel> getAccessory() {
        return accessory;
    }

    public void setAccessory(ArrayList<ComCommodityEntryPreAlertModel> accessoty) {
        this.accessory = accessoty;
    }
    
}
