/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
/**
 *
 * @author HungLM
 */
public class FrequentItemset {
    int size;
    ArrayList<Candidate> itemSet;

    public FrequentItemset() {
        this.size=1;
        this.itemSet = new ArrayList<>();
    }

    public FrequentItemset(int size, ArrayList<Candidate> itemSet) {
        this.size = size;
        this.itemSet = itemSet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Candidate> getItemSet() {
        return itemSet;
    }

    public void setItemSet(ArrayList<Candidate> itemSet) {
        this.itemSet = itemSet;
    }
    
    public boolean hasCandidate(Candidate c){
        int i = 0;
        for(Candidate tmpC : this.getItemSet()){
            if(tmpC.isEqual(c))
                return true;
            else
                i++;
        }
        if(i == this.getItemSet().size())
            return false;
        else
            return true;
    }
    
}
