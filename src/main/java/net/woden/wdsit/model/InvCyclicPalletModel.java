package net.woden.wdsit.model;

public class InvCyclicPalletModel {
    private int id;
    private String number;
    private String type;
    private String sapCode;
    private String location;
    private int quantity;
    private String couting1;
    private String couting2;
    private String couting3;
    private String couting4;
    private String status;
    private String assistant;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCouting1() {
        return couting1;
    }

    public void setCouting1(String couting1) {
        this.couting1 = couting1;
    }

    public String getCouting2() {
        return couting2;
    }

    public void setCouting2(String couting2) {
        this.couting2 = couting2;
    }

    public String getCouting3() {
        return couting3;
    }

    public void setCouting3(String couting3) {
        this.couting3 = couting3;
    }

    public String getCouting4() {
        return couting4;
    }

    public void setCouting4(String couting4) {
        this.couting4 = couting4;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
