package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvSerialEntity implements Serializable {
    @Id
    private int id;
    private int coutingId;
    private String serial;
    private String mac;
    private String sapCode;
    private String sapCodeSap;
    private String sapCodeWms;
    private String sapCodeBase;
    private String status;
    private String statusSap;
    private String statusWms;
    private String statusBase;
    private String pallet;
    private String palletSap;
    private String palletWms;
    private String adjustment;
    private String creationDate;
    private int creationUserId;
    private String creationUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoutingId() {
        return coutingId;
    }

    public void setCoutingId(int coutingId) {
        this.coutingId = coutingId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getSapCodeSap() {
        return sapCodeSap;
    }

    public void setSapCodeSap(String sapCodeSap) {
        this.sapCodeSap = sapCodeSap;
    }

    public String getSapCodeWms() {
        return sapCodeWms;
    }

    public void setSapCodeWms(String sapCodeWms) {
        this.sapCodeWms = sapCodeWms;
    }

    public String getSapCodeBase() {
        return sapCodeBase;
    }

    public void setSapCodeBase(String sapCodeBase) {
        this.sapCodeBase = sapCodeBase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusSap() {
        return statusSap;
    }

    public void setStatusSap(String statusSap) {
        this.statusSap = statusSap;
    }

    public String getStatusWms() {
        return statusWms;
    }

    public void setStatusWms(String statusWms) {
        this.statusWms = statusWms;
    }

    public String getStatusBase() {
        return statusBase;
    }

    public void setStatusBase(String statusBase) {
        this.statusBase = statusBase;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getPalletSap() {
        return palletSap;
    }

    public void setPalletSap(String palletSap) {
        this.palletSap = palletSap;
    }

    public String getPalletWms() {
        return palletWms;
    }

    public void setPalletWms(String palletWms) {
        this.palletWms = palletWms;
    }

    public String getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }
}
