package net.woden.wdsit.model;

public class InvLocationCoutingModel {
    private String location;
    private String sapCode;
    private int coutingFirst;
    private int coutingSecond;
    private int coutingThird;
    private int coutingFourth;

    public String getLocation() {
        return location;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCoutingFirst() {
        return coutingFirst;
    }

    public void setCoutingFirst(int coutingFirst) {
        this.coutingFirst = coutingFirst;
    }

    public int getCoutingSecond() {
        return coutingSecond;
    }

    public void setCoutingSecond(int coutingSecond) {
        this.coutingSecond = coutingSecond;
    }

    public int getCoutingThird() {
        return coutingThird;
    }

    public void setCoutingThird(int coutingThird) {
        this.coutingThird = coutingThird;
    }

    public int getCoutingFourth() {
        return coutingFourth;
    }

    public void setCoutingFourth(int coutingFourth) {
        this.coutingFourth = coutingFourth;
    }
}
