/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.entity;

/**
 *
 * @author b.algecira
 */
public class ScpTextAudiEntity {
    
    private int id;
    private String name;
    private String description;
    private String creationDate;
    private int userIdCreation;
    private String updateDate;
    private int userIdUpdate;
    private Boolean active;

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserIdCreation() {
        return userIdCreation;
    }

    public void setUserIdCreation(int userIdCreation) {
        this.userIdCreation = userIdCreation;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserIdUpdate() {
        return userIdUpdate;
    }

    public void setUserIdUpdate(int userIdUpdate) {
        this.userIdUpdate = userIdUpdate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
}
