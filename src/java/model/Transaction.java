/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author HungLM
 */
public class Transaction {
    private ArrayList<String> listFactID;
    private ArrayList<Fact> listFact;

    public Transaction() {
        this.listFactID = new ArrayList<>();
        this.listFact = new ArrayList<>();
    }

    public Transaction(ArrayList<String> listFactID, ArrayList<Fact> listFact) {
        this.listFactID = listFactID;
        this.listFact = listFact;
    }

    public ArrayList<String> getListFactID() {
        return listFactID;
    }

    public void setListFactID(ArrayList<String> listFactID) {
        this.listFactID = listFactID;
    }

    public ArrayList<Fact> getListFact() {
        return listFact;
    }

    public void setListFact(ArrayList<Fact> listFact) {
        this.listFact = listFact;
    }
    
    public boolean hasFact(Fact f){
        int i = 0;
        for(Fact tmpF : this.getListFact()){
            if(tmpF.isEqual(f))
                return true;
            else
                i++;
        }
        if(i == this.getListFact().size())
            return false;
        else 
            return true;
    }
    
    public boolean hasAllFact (ArrayList<Fact> facts){
        int t = 0;
        for(Fact f : facts){
            if(!this.hasFact(f))
                return false;
            else
                t++;
        }
        if(t == facts.size())
            return true;
        else
            return false;
    }
}
