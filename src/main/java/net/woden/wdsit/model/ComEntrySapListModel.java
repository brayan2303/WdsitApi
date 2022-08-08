/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.model;

import java.util.ArrayList;

/**
 *
 * @author m.pulido
 */
public class ComEntrySapListModel {
    
    private int entrySapB1Id;
    private ArrayList<ComEntrySapListEntryModel> entrys;

    public int getEntrySapB1Id() {
        return entrySapB1Id;
    }

    public void setEntrySapB1Id(int entrySapB1Id) {
        this.entrySapB1Id = entrySapB1Id;
    }

    public ArrayList<ComEntrySapListEntryModel> getEntrys() {
        return entrys;
    }

    public void setEntrys(ArrayList<ComEntrySapListEntryModel> entrys) {
        this.entrys = entrys;
    }
    
    
    
}
