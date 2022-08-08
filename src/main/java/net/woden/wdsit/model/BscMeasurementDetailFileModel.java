package net.woden.wdsit.model;

public class BscMeasurementDetailFileModel {
    private int measurementDetailId;
    private String name;
    private byte[]file;
    private String type;

    public int getMeasurementDetailId() {
        return measurementDetailId;
    }

    public void setMeasurementDetailId(int measurementDetailId) {
        this.measurementDetailId = measurementDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
