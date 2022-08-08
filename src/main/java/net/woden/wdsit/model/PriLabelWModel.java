package net.woden.wdsit.model;

import java.util.ArrayList;

public class PriLabelWModel {
    private String[]columns;
    private ArrayList<Object>rows;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public ArrayList<Object> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Object> rows) {
        this.rows = rows;
    }
}
