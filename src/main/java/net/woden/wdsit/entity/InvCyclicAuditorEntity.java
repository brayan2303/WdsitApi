package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvCyclicAuditorEntity implements Serializable {
    @Id
    private int id;
    private int cyclicId;
    private int personId;
    private String person;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCyclicId() {
        return cyclicId;
    }

    public void setCyclicId(int cyclicId) {
        this.cyclicId = cyclicId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
