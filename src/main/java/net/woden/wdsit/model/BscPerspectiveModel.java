package net.woden.wdsit.model;

public class BscPerspectiveModel {
    private int id;
    private String name;
    private int strategicObjetives;
    private int indicators;

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

    public int getStrategicObjetives() {
        return strategicObjetives;
    }

    public void setStrategicObjetives(int strategicObjetives) {
        this.strategicObjetives = strategicObjetives;
    }

    public int getIndicators() {
        return indicators;
    }

    public void setIndicators(int indicators) {
        this.indicators = indicators;
    }
}
