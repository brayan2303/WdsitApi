package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvCardEntity implements Serializable {
    @Id
    private int id;
    private String code;
    private int cyclicId;
    private String coutingType;
    private String signed1;
    private String signed2;
    private String date;
    private String status;
    private int auditorId;
    private String auditor;
    private String cyclic;
    private String customer;
    private String pallet;
    private String loadDate;
    private String location;
    private int total;
    private int sampling;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCyclicId() {
        return cyclicId;
    }

    public void setCyclicId(int cyclicId) {
        this.cyclicId = cyclicId;
    }

    public String getCoutingType() {
        return coutingType;
    }

    public void setCoutingType(String coutingType) {
        this.coutingType = coutingType;
    }
    
    public String getSigned1() {
        return signed1;
    }

    public void setSigned1(String signed1) {
        this.signed1 = signed1;
    }

    public String getSigned2() {
        return signed2;
    }

    public void setSigned2(String signed2) {
        this.signed2 = signed2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getCyclic() {
        return cyclic;
    }

    public void setCyclic(String cyclic) {
        this.cyclic = cyclic;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSampling() {
        return sampling;
    }

    public void setSampling(int sampling) {
        this.sampling = sampling;
    }
}
