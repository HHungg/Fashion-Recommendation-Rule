/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import ConnectDB.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HungLM
 */
public class Rule implements Comparator<Rule>{
    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;
    
    private ArrayList<String> left;
    private String right;
    private boolean isUsed;
    private int rank;
    private String explaintion;
    private int ruleID;

    public Rule() {
    }

    public Rule(ArrayList<String> left, String right, boolean isUsed, int rank, String explaintion, int ruleID) {
        this.left = left;
        this.right = right;
        this.isUsed = isUsed;
        this.rank = rank;
        this.explaintion = explaintion;
        this.ruleID = ruleID;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public ArrayList<String> getLeft() {
        return left;
    }

    public void setLeft(ArrayList<String> left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getExplaintion() {
        return explaintion;
    }

    public void setExplaintion(String explaintion) {
        this.explaintion = explaintion;
    }

    public int getRuleID() {
        return ruleID;
    }

    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }
    
    @Override
    public int compare(Rule r1, Rule r2) {
        if(r1.rank - r2.rank == 0){
            return r2.left.size() - r1.left.size();
        }else{
            return r2.rank - r1.rank;
        }
    }
    
    public void updateRank( int rank){
        this.rank = rank;
        try {
            Connection con=null;
            con=ConnectionDB.getConnection();
            String query = " Update Luat Set Do_tin_cay ="+rank+" Where ID = "+this.ruleID;
//            System.out.println(query);
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public boolean isContent(ArrayList<String> facts){
       boolean result = true;
       int check = 0;
       for(String supposition : left){
           check = 0;
           for(String fact : facts){
               if(fact.equals(supposition)){
                  check++;
                  break;
               }
           }
           if(check == 0) return false;
       }
       return result;
   }
}
