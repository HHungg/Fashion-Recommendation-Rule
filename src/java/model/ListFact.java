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
import java.util.ArrayList;

/**
 *
 * @author HungLM
 */
public class ListFact {
    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;
    
    private ArrayList<String> listFactID;
    private ArrayList<Fact> listFact;

    public ListFact() {
        try {
            con = ConnectionDB.getConnection();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        this.getDBListFact();
        this.getDBListFactID();
    }

    public ListFact(ArrayList<String> listFactID, ArrayList<Fact> listFact) {
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
    
    public void getDBListFactID(){
        String sql = "SELECT ID FROM Su_kien;";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                String id = new String();
                id = rs.getString("ID");
                this.listFactID.add(id); 
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void getDBListFact(){
        String sql = "SELECT * FROM Su_kien;";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Fact f = new Fact();
                f.setFactID(rs.getString("ID"));
                f.setFaceName(rs.getString("Ten_su_kien"));
                this.listFact.add(f); 
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
