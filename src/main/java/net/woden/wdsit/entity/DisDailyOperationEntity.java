package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DisDailyOperationEntity implements Serializable {
    @Id
    private int id;
    private int countryId;
    private String country;
    private int departmentId;
    private String department;
    private int cityId;
    private String city;
    private int customerId;
    private String customer;
    private int year;
    private int monthId;
    private int accumulatedLogistic;
    private int goalLogistic;
    private int accumulatedProduction;
    private int goalProduction;
    private int accumulatedReconditioning;
    private int goalReconditioning;
    private int accumulatedMakeover;
    private int goalMakeover;
    private int dispatch;
    private int accumulatedRepair;
    private int goalRepair;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public int getAccumulatedLogistic() {
        return accumulatedLogistic;
    }

    public void setAccumulatedLogistic(int accumulatedLogistic) {
        this.accumulatedLogistic = accumulatedLogistic;
    }

    public int getGoalLogistic() {
        return goalLogistic;
    }

    public void setGoalLogistic(int goalLogistic) {
        this.goalLogistic = goalLogistic;
    }

    public int getAccumulatedProduction() {
        return accumulatedProduction;
    }

    public void setAccumulatedProduction(int accumulatedProduction) {
        this.accumulatedProduction = accumulatedProduction;
    }

    public int getGoalProduction() {
        return goalProduction;
    }

    public void setGoalProduction(int goalProduction) {
        this.goalProduction = goalProduction;
    }

    public int getAccumulatedReconditioning() {
        return accumulatedReconditioning;
    }

    public void setAccumulatedReconditioning(int accumulatedReconditioning) {
        this.accumulatedReconditioning = accumulatedReconditioning;
    }

    public int getGoalReconditioning() {
        return goalReconditioning;
    }

    public void setGoalReconditioning(int goalReconditioning) {
        this.goalReconditioning = goalReconditioning;
    }

    public int getAccumulatedMakeover() {
        return accumulatedMakeover;
    }

    public void setAccumulatedMakeover(int accumulatedMakeover) {
        this.accumulatedMakeover = accumulatedMakeover;
    }

    public int getGoalMakeover() {
        return goalMakeover;
    }

    public void setGoalMakeover(int goalMakeover) {
        this.goalMakeover = goalMakeover;
    }

    public int getDispatch() {
        return dispatch;
    }

    public void setDispatch(int dispatch) {
        this.dispatch = dispatch;
    }

    public int getAccumulatedRepair() {
        return accumulatedRepair;
    }

    public void setAccumulatedRepair(int accumulatedRepair) {
        this.accumulatedRepair = accumulatedRepair;
    }

    public int getGoalRepair() {
        return goalRepair;
    }

    public void setGoalRepair(int goalRepair) {
        this.goalRepair = goalRepair;
    }
}
