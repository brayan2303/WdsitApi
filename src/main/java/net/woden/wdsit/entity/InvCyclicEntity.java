package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvCyclicEntity implements Serializable {
    @Id
    private int id;
    private String name;
    private String typeSampling;
    private int sampling;
    private int total;
    private String system;
    private boolean crossSap;
    private boolean crossWms;
    private boolean crossBase;
    private String principalSystem;
    private String creationDate;
    private String status;
    private int countryId;
    private String country;
    private int customerId;
    private String customer;
    private int personId;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeSampling() {
        return typeSampling;
    }

    public void setTypeSampling(String typeSampling) {
        this.typeSampling = typeSampling;
    }

    public int getSampling() {
        return sampling;
    }

    public void setSampling(int sampling) {
        this.sampling = sampling;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public boolean isCrossSap() {
        return crossSap;
    }

    public void setCrossSap(boolean crossSap) {
        this.crossSap = crossSap;
    }

    public boolean isCrossWms() {
        return crossWms;
    }

    public void setCrossWms(boolean crossWms) {
        this.crossWms = crossWms;
    }

    public boolean isCrossBase() {
        return crossBase;
    }

    public void setCrossBase(boolean crossBase) {
        this.crossBase = crossBase;
    }
    
    public String getPrincipalSystem() {
        return principalSystem;
    }

    public void setPrincipalSystem(String principalSystem) {
        this.principalSystem = principalSystem;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
