package net.woden.wdsit.model;

public class BscVariableValueModel {
    private int formulaVariableId;
    private String type;
    private String name;
    private int detailVariableId;
    private double value;
    private int defaultValue;

    public int getFormulaVariableId() {
        return formulaVariableId;
    }

    public void setFormulaVariableId(int formulaVariableId) {
        this.formulaVariableId = formulaVariableId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDetailVariableId() {
        return detailVariableId;
    }

    public void setDetailVariableId(int detailVariableId) {
        this.detailVariableId = detailVariableId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
}
