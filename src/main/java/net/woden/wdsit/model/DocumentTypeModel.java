package net.woden.wdsit.model;

/**
 *
 * @author f.casallas
 */
public class DocumentTypeModel {

    private int id;
    private String groupDocument;
    private String description;
    private String groupName;
    private String idCode;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupDocument() {
        return groupDocument;
    }

    public void setGroupDocument(String groupDocument) {
        this.groupDocument = groupDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

}
