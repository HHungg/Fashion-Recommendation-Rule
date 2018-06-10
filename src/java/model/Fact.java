/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HungLM
 */
public class Fact {
    private String factID;
    private String factName;

    public Fact() {
        this.factID = new String();
        this.factName = new String();
    }

    public Fact(String factID, String factName) {
        this.factID = factID;
        this.factName = factName;
    }

    public String getFactID() {
        return factID;
    }

    public void setFactID(String factID) {
        this.factID = factID;
    }

    public String getFaceName() {
        return factName;
    }

    public void setFaceName(String factName) {
        this.factName = factName;
    }
    
    public boolean isEqual(Fact f){
        if(this.getFaceName().equals(f.getFaceName()) && this.getFactID().equals(f.getFactID()))
            return true;
        else return false;
    }
}
