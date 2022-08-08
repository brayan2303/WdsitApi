package net.woden.wdsit.model;

public class DisMonthModel {
    private int id;
    private int year;
    private int monthId;
    private String month;
    private int days;
    private int daysExcluded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDaysExcluded() {
        return daysExcluded;
    }

    public void setDaysExcluded(int daysExcluded) {
        this.daysExcluded = daysExcluded;
    }
}
