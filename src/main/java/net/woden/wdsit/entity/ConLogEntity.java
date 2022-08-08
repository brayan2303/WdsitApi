package net.woden.wdsit.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConLogEntity implements Serializable {
    @Id
    private int id;
    private int controlPanelId;
    private Date viewDate;
    private int personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getControlPanelId() {
        return controlPanelId;
    }

    public void setControlPanelId(int controlPanelId) {
        this.controlPanelId = controlPanelId;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
