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
/**
 *
 * @author HungLM
 */
public class Suggestion {
    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;
    
    private ArrayList<Fact> left;
    private ArrayList<Fact> right;


    public Suggestion() {
        try {
            con = ConnectionDB.getConnection();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();
    }

    public Suggestion(ArrayList<Fact> left, ArrayList<Fact> right) {
        this.left = left;
        this.right = right;
    }

    public ArrayList<Fact> getLeft() {
        return left;
    }

    public void setLeft(ArrayList<Fact> left) {
        this.left = left;
    }

    public ArrayList<Fact> getRight() {
        return right;
    }

    public void setRight(ArrayList<Fact> right) {
        this.right = right;
    }

    public String print(){
        String s = new String();
        for(Fact f : this.getLeft()){
            s = s + f.getFactID();
            s = s + " ";
        }
        s = s + "-> ";
        for(Fact f : this.getRight()){
            s = s + f.getFactID();
            s = s + " ";
        }
        return s;
        
    }
    
    public void addRule() throws SQLException{
        if(this.getRight().size() == 1){
            ArrayList<String> left = new ArrayList<>();
            ArrayList<String> right = new ArrayList<>();
            String explaintion = new String("NULL");
            int rank = 0;
            for(Fact f : this.getLeft()){
                left.add(f.getFactID());
            }
            for(Fact f : this.getRight()){
                right.add(f.getFactID());
            }
            Connection con = null;
            con = ConnectionDB.getConnection();
            String query = " insert into Luat(Ve_trai, Ve_phai, Giai_thich, Do_tin_cay) value(N'"+left+"',N'"+right+"',N'"+explaintion+"',"+rank+")";
            PreparedStatement prepare = con.prepareStatement(query);
            prepare.executeUpdate();
        }
    }  
    
    public Rule toRule() {
        Rule r = new Rule();
        if (this.getRight().size() == 1) {
            ArrayList<String> left = new ArrayList<>();
            ArrayList<String> right = new ArrayList<>();
            String explaintion = new String();
            int rank = 0;
            for (Fact f : this.getLeft()) {
                left.add(f.getFactID());
            }
            for (Fact f : this.getRight()) {
                right.add(f.getFactID());
            }
            r.setLeft(left);
            r.setRight(right.get(0));
        }
        return r;
    }
}
