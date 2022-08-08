/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

/**
 *
 * @author b.algecira
 */
public class FileSerialSearchModel {
    
    private int id;
    private String CardName;
    private int cardNameId;
    private String Serial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String CardName) {
        this.CardName = CardName;
    }

    public int getCardNameId() {
        return cardNameId;
    }

    public void setCardNameId(int cardNameId) {
        this.cardNameId = cardNameId;
    }
    
    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }
    
    
    
    
}
