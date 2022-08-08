package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConControlPanelPersonEntity implements Serializable {
    @Id
    private int id;
    private int ControlPanelId;
    private int personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getControlPanelId() {
        return ControlPanelId;
    }

    public void setControlPanelId(int ControlPanelId) {
        this.ControlPanelId = ControlPanelId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
