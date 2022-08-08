package net.woden.wdsit.model;

public class BscAdvanceFileModel {
    private int advanceId;
    private String name;
    private byte[]file;
    private String type;
    
    public int getAdvanceId() {
        return advanceId;
    }

    public void setAdvanceId(int advanceId) {
        this.advanceId = advanceId;
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
