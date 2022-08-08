package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DisDailyOperationDayEntity implements Serializable {
    @Id
    private int id;
    private int dailyOperationId;
    private int day;
    private int logistic;
    private int production;
    private int reconditioning;
    private int makeover;
    private int repair;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDailyOperationId() {
        return dailyOperationId;
    }

    public void setDailyOperationId(int dailyOperationId) {
        this.dailyOperationId = dailyOperationId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLogistic() {
        return logistic;
    }

    public void setLogistic(int logistic) {
        this.logistic = logistic;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getReconditioning() {
        return reconditioning;
    }

    public void setReconditioning(int reconditioning) {
        this.reconditioning = reconditioning;
    }

    public int getMakeover() {
        return makeover;
    }

    public void setMakeover(int makeover) {
        this.makeover = makeover;
    }

    public int getRepair() {
        return repair;
    }

    public void setRepair(int repair) {
        this.repair = repair;
    }
}
