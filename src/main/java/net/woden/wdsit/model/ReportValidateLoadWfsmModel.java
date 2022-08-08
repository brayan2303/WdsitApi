/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.model;

import java.util.Date;

/**
 *
 * @author b.algecira
 */
public class ReportValidateLoadWfsmModel {
    
    private String collectionDate;
    private String contractorName;
    private String modelCode;
    private String modelDescription;
    private String cpeCategory;
    private String dxType;
    private String serialOne;
    private String address;
    private String zoneType;
    private String region;
    private String city;
    private String departement;
    private String country;
    private String CodigoSap;
    private String NombreSap;
    private String CodigoFamilia;

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getSerialOne() {
        return serialOne;
    }

    public void setSerialOne(String serialOne) {
        this.serialOne = serialOne;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public String getCpeCategory() {
        return cpeCategory;
    }

    public void setCpeCategory(String cpeCategory) {
        this.cpeCategory = cpeCategory;
    }

    public String getDxType() {
        return dxType;
    }

    public void setDxType(String dxType) {
        this.dxType = dxType;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCodigoSap() {
        return CodigoSap;
    }

    public void setCodigoSap(String CodigoSap) {
        this.CodigoSap = CodigoSap;
    }

    public String getNombreSap() {
        return NombreSap;
    }

    public void setNombreSap(String NombreSap) {
        this.NombreSap = NombreSap;
    }

    public String getCodigoFamilia() {
        return CodigoFamilia;
    }

    public void setCodigoFamilia(String CodigoFamilia) {
        this.CodigoFamilia = CodigoFamilia;
    }

    public void setCollectionDate(Date dateConvert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
