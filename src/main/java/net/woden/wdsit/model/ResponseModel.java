package net.woden.wdsit.model;

public class ResponseModel {
    private String title;
    private String message;
    private Object object;
    private int statusCode;

    public ResponseModel() {
    }

    public ResponseModel(String title, String message, Object object, int statusCode) {
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
