package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BscAnalysisEntity implements Serializable {
    @Id
    private int id;
    private int measurementDetailId;
    private String analysis;
    private String perspective;
    private String strategicObjetive;
    private String indicator;
    private String month;
    private int actionPlanId;
    private String actionPlan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeasurementDetailId() {
        return measurementDetailId;
    }

    public void setMeasurementDetailId(int measurementDetailId) {
        this.measurementDetailId = measurementDetailId;
    }
    
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

    public String getStrategicObjetive() {
        return strategicObjetive;
    }

    public void setStrategicObjetive(String strategicObjetive) {
        this.strategicObjetive = strategicObjetive;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public int getActionPlanId() {
        return actionPlanId;
    }

    public void setActionPlanId(int actionPlanId) {
        this.actionPlanId = actionPlanId;
    }
    
    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }
}
