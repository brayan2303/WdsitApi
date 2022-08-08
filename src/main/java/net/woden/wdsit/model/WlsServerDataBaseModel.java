package net.woden.wdsit.model;

public class WlsServerDataBaseModel {
    private String name;
    private String masterFile;
    private int masterSize;
    private String logsFile;
    private int logsSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterFile() {
        return masterFile;
    }

    public void setMasterFile(String masterFile) {
        this.masterFile = masterFile;
    }

    public int getMasterSize() {
        return masterSize;
    }

    public void setMasterSize(int masterSize) {
        this.masterSize = masterSize;
    }

    public String getLogsFile() {
        return logsFile;
    }

    public void setLogsFile(String logsFile) {
        this.logsFile = logsFile;
    }

    public int getLogsSize() {
        return logsSize;
    }

    public void setLogsSize(int logsSize) {
        this.logsSize = logsSize;
    }
}
