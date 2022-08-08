package net.woden.wdsit.model;

import org.json.JSONArray;

public class ResponseJsonArrayModel {
    private String title;
    private String message;
    private JSONArray object;
    private int statusCode;

    public ResponseJsonArrayModel() {
    }

    public ResponseJsonArrayModel(String title, String message, JSONArray object, int statusCode) {
        this.title = title;
        this.message = message;
        this.object = object;
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONArray getObject() {
        return object;
    }

    public void setObject(JSONArray object) {
        this.object = object;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
