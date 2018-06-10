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
public class Candidate {

    private int size;
    private ArrayList<Fact> factSet;

    public Candidate() {
        this.size = 1;
        this.factSet = new ArrayList<>();
    }

    public Candidate(int size, ArrayList<Fact> itemSet) {
        this.size = size;
        this.factSet = itemSet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Fact> getFactSet() {
        return factSet;
    }

    public void setFactSet(ArrayList<Fact> itemSet) {
        this.factSet = itemSet;
    }

    public boolean isEqual(Candidate c) {
        if (this.getSize() != c.getSize() || this.getFactSet().size() != c.getFactSet().size()) {
            return false;
        }
        for (int i = 0; i < this.getFactSet().size(); i++) {
            if (!this.getFactSet().get(i).getFactID().equals(c.getFactSet().get(i).getFactID())) {
                return false;
            }
        }
        return true;
    }

    public double calculateSup(ArrayList<Transaction> T) {
        double i = 0;
        for (Transaction t : T) {
            if (t.hasAllFact(this.getFactSet())) {
                i++;
            }
        }
        return i/T.size();
    }
    
    public boolean hasFact(Fact f){
        int i = 0;
        for(Fact tmpF : this.getFactSet()){
            if(tmpF.isEqual(f))
                return true;
            else
                i++;
        }
        if(i == this.getFactSet().size())
            return false;
        else 
            return true;
    }

}
