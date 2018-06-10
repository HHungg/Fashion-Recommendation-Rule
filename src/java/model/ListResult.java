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
public class ListResult {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement stmt;
    
    private ArrayList<String> listResultID;
    private ArrayList<Fact> listResult;

    public ListResult() {
        try {
            con = ConnectionDB.getConnection();
            
        } catch (Exception e) {
        }
        this.getDBResult();
        this.getDBResultID();
    }

    public ListResult(ArrayList<String> listResultID, ArrayList<Fact> listResult) {
        this.listResultID = listResultID;
        this.listResult = listResult;
    }

    public ArrayList<String> getListResultID() {
        return listResultID;
    }

    public void setListResultID(ArrayList<String> listResultID) {
        this.listResultID = listResultID;
    }

    public ArrayList<Fact> getListResult() {
        return listResult;
    }

    public void setListResult(ArrayList<Fact> listResult) {
        this.listResult = listResult;
    }

    
    
    public void getDBResultID(){
        String sql = "SELECT ID FROM Ket_luan;";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<String> tmpListID = new ArrayList<>();
            while(rs.next()){
                String id = new String();
                id = rs.getString("ID");
                tmpListID.add(id); 
            }
            this.setListResultID(tmpListID);
        } catch (Exception e) {
              System.out.println(e.toString());
        }
    }
    
    public void getDBResult(){
        String sql = "SELECT * FROM Ket_luan;";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Fact> tmpListResult = new ArrayList<>();
            while(rs.next()){
                Fact kl = new Fact();
                kl.setFactID(rs.getString("ID"));
                kl.setFaceName(rs.getString("Ten_ket_luan"));
                tmpListResult.add(kl); 
            }
            this.setListResult(tmpListResult);
        } catch (Exception e) {
              System.out.println(e.toString());
        }
    }
}
