package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BscMeasurementEntity implements Serializable {
    @Id
    private int id;
    private String proyectPlan;
    private int perspectiveId;
    private String perspective;
    private int strategicObjetiveId;
    private String strategicObjetive;
    private int indicatorId;
    private String indicator;
    private String direction;
    private int formulaId;
    private String formula;
    private int frecuencyId;
    private String frecuency;
    private int year;
    private String goalType;
    private double goal;
    private int responsibleUserId;
    private String responsibleUser;
    private int leaderId;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getProyectPlan() {
        return proyectPlan;
    }

    public void setProyectPlan(String proyectPlan) {
        this.proyectPlan = proyectPlan;
    }

    public int getPerspectiveId() {
        return perspectiveId;
    }

    public void setPerspectiveId(int perspectiveId) {
        this.perspectiveId = perspectiveId;
    }

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

    public int getStrategicObjetiveId() {
        return strategicObjetiveId;
    }

    public void setStrategicObjetiveId(int strategicObjetiveId) {
        this.strategicObjetiveId = strategicObjetiveId;
    }

    public String getStrategicObjetive() {
        return strategicObjetive;
    }

    public void setStrategicObjetive(String strategicObjetive) {
        this.strategicObjetive = strategicObjetive;
    }

    public int getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(int indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
    
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public int getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    
    public int getFrecuencyId() {
        return frecuencyId;
    }

    public void setFrecuencyId(int frecuencyId) {
        this.frecuencyId = frecuencyId;
    }

    public String getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(String frecuency) {
        this.frecuency = frecuency;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public int getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(int responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public String getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(String responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
