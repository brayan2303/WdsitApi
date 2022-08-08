package net.woden.wdsit.entity;

public class MasApprovalEntity {
    private int id;
    private int mailId;
    private String mail;
    private int approvalUserId;
    private String approvalUser;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(int approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
