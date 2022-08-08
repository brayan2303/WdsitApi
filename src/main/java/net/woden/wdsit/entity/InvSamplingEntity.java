package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvSamplingEntity implements Serializable {
    @Id
    private int id;
    private int cyclicId;
    private String value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
