package net.woden.wdsit.model;

/**
 *
 * @author f.casallas
 */
public class PqrsClientSerialSModel {

    private String ticket;
    private int creationPersonId;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getCreationPersonId() {
        return creationPersonId;
    }

    public void setCreationPersonId(int creationPersonId) {
        this.creationPersonId = creationPersonId;
    }

}
