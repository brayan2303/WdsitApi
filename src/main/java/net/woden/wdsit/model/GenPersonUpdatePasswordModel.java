package net.woden.wdsit.model;

/**
 *
 * @author f.casallas
 */
public class GenPersonUpdatePasswordModel {
    private String passwordNew;
    private String passwordOld;

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }
    
}
