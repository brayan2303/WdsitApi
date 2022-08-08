package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GenApplicationPersonProfileEntity implements Serializable {
    @Id
    private int id;
    private int applicationPersonId;
    private int profileId;
    private String profile;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicationPersonId() {
        return applicationPersonId;
    }

    public void setApplicationPersonId(int applicationPersonId) {
        this.applicationPersonId = applicationPersonId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
