package net.woden.wdsit.model;

import java.util.ArrayList;
import net.woden.wdsit.entity.BscNotificationUserEntity;

public class BscNotificationModel {
    private ArrayList<BscNotificationUserEntity>mails;
    private ArrayList<String>variables;

    public ArrayList<BscNotificationUserEntity> getMails() {
        return mails;
    }

    public void setMails(ArrayList<BscNotificationUserEntity> mails) {
        this.mails = mails;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }
}
