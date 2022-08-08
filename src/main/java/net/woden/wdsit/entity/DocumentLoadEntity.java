package net.woden.wdsit.entity;

/**
 *
 * @author f.casallas
 */
public class DocumentLoadEntity {

    private int id;
    private int documentId;
    private String documentName;
    private int userPropertyId;
    private int userPropertyIdentification;
    private String userPropertyName;
    private String documentPropertyName;
    private String extensionProperty;
    private String documentSaveName;
    private int version;
    private Boolean active;
    private int creationUserId;
    private String creationDate;
    private String updateDate;
    private String costCenter;
    private int updateUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getUserPropertyId() {
        return userPropertyId;
    }

    public void setUserPropertyId(int userPropertyId) {
        this.userPropertyId = userPropertyId;
    }

    public int getUserPropertyIdentification() {
        return userPropertyIdentification;
    }

    public void setUserPropertyIdentification(int userPropertyIdentification) {
        this.userPropertyIdentification = userPropertyIdentification;
    }

    public String getUserPropertyName() {
        return userPropertyName;
    }

    public void setUserPropertyName(String userPropertyName) {
        this.userPropertyName = userPropertyName;
    }

    public String getDocumentPropertyName() {
        return documentPropertyName;
    }

    public void setDocumentPropertyName(String documentPropertyName) {
        this.documentPropertyName = documentPropertyName;
    }

    public String getExtensionProperty() {
        return extensionProperty;
    }

    public void setExtensionProperty(String extensionProperty) {
        this.extensionProperty = extensionProperty;
    }

    public String getDocumentSaveName() {
        return documentSaveName;
    }

    public void setDocumentSaveName(String documentSaveName) {
        this.documentSaveName = documentSaveName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }
        
}
