package net.woden.wdsit.entity;

public class MasAccountEntity {

    private int id;
    private String mail;
    private String name;
    private String position;
    private String provider;
    private int port;
    private String password;
    private String creationDate;
    private int creationUserId;
    private String creationUser;
    private boolean active;

    public MasAccountEntity() {
    }

    public MasAccountEntity(int id, String mail, String name, String position, String provider, int port, String password, String creationDate, int creationUserId, String creationUser, boolean active) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.position = position;
        this.provider = provider;
        this.port = port;
        this.password = password;
        this.creationDate = creationDate;
        this.creationUserId = creationUserId;
        this.creationUser = creationUser;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
