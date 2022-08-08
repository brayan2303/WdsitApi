/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

import java.util.ArrayList;
import net.woden.wdsit.entity.ProNotificationUserEntity;


/**
 *
 * @author b.algecira
 */
public class ProNotificationModel {
    
    private ArrayList<ProNotificationUserEntity>mails;
    private ArrayList<String>variables;

    public ArrayList<ProNotificationUserEntity> getMails() {
        return mails;
    }

    public void setMails(ArrayList<ProNotificationUserEntity> mails) {
        this.mails = mails;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }
    
    
}
