/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author b.algecira
 */
public class CovFormSegPerEntity implements Serializable {
    
    private int id;
    private int identificationUser;
    private String name;
    private String creationUser;
    private String position;
    private String carPosition;
    private String area;
    private String cityId;
    private String city;
    private Date creationDate;
    private String meetingPlace;
    private String cough;
    private String bodyPain;
    private String fatigue;
    private String soreThroat;
    private String headache;
    private String runnyNose;
    private String respiratoryDistress;
    private String smellStaste;
    private String temperature;
    private String contactPerson;
    private String closeContact;
    private String bloodTest;
    private String noseTest;
    private String positiveIsolation;
    private String positiveDisability;
    private String placeOutside;
    private String placeInside;
    private String persons;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentificationUser() {
        return identificationUser;
    }

    public void setIdentificationUser(int identificationUser) {
        this.identificationUser = identificationUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCarPosition() {
        return carPosition;
    }

    public void setCarPosition(String carPosition) {
        this.carPosition = carPosition;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getBodyPain() {
        return bodyPain;
    }

    public void setBodyPain(String bodyPain) {
        this.bodyPain = bodyPain;
    }

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    public String getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    public String getRunnyNose() {
        return runnyNose;
    }

    public void setRunnyNose(String runnyNose) {
        this.runnyNose = runnyNose;
    }

    public String getRespiratoryDistress() {
        return respiratoryDistress;
    }

    public void setRespiratoryDistress(String respiratoryDistress) {
        this.respiratoryDistress = respiratoryDistress;
    }

    public String getSmellStaste() {
        return smellStaste;
    }

    public void setSmellStaste(String smellStaste) {
        this.smellStaste = smellStaste;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCloseContact() {
        return closeContact;
    }

    public void setCloseContact(String closeContact) {
        this.closeContact = closeContact;
    }

    public String getBloodTest() {
        return bloodTest;
    }

    public void setBloodTest(String bloodTest) {
        this.bloodTest = bloodTest;
    }

    public String getNoseTest() {
        return noseTest;
    }

    public void setNoseTest(String noseTest) {
        this.noseTest = noseTest;
    }

    public String getPositiveIsolation() {
        return positiveIsolation;
    }

    public void setPositiveIsolation(String positiveIsolation) {
        this.positiveIsolation = positiveIsolation;
    }

    public String getPositiveDisability() {
        return positiveDisability;
    }

    public void setPositiveDisability(String positiveDisability) {
        this.positiveDisability = positiveDisability;
    }

    public String getPlaceOutside() {
        return placeOutside;
    }

    public void setPlaceOutside(String placeOutside) {
        this.placeOutside = placeOutside;
    }

    public String getPlaceInside() {
        return placeInside;
    }

    public void setPlaceInside(String placeInside) {
        this.placeInside = placeInside;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }
    
    
    
}
