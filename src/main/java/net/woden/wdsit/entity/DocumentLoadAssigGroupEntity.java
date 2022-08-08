package net.woden.wdsit.entity;

/**
 *
 * @author f.casallas
 */
public class DocumentLoadAssigGroupEntity {

    public int id;
    private int userId;
    public int groupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
