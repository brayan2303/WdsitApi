package net.woden.wdsit.model;

public class InvPalletCoutingModel {
    private String coutingType;
    private String status;
    private String type;
    private int quantity;
    private String startDate;
    private String endDate;
    private String assistant;

    public String getCoutingType() {
        return coutingType;
    }

    public void setCoutingType(String coutingType) {
        this.coutingType = coutingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }
}
