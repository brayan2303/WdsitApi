package net.woden.wdsit.entity;

/**
 *
 * @author f.casallas
 */
public class DocumentTypeEntity {

    private int id;
    private String groupDocument;
    private String description;
    private int idGroup;
    private String groupName;
    private int createUserId;
    private boolean active;
    private int updateUserId;
    private int idCode;
    private String codeName;

    public int getId() {
        return id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
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

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
    
}
