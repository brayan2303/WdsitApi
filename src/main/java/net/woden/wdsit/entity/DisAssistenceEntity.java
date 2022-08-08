package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DisAssistenceEntity implements Serializable {
    @Id
    private int id;
    private int monthId;
    private int headCountId;
    private int day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public int getHeadCountId() {
        return headCountId;
    }

    public void setHeadCountId(int headCountId) {
        this.headCountId = headCountId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
