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
import java.util.Arrays;

/**
 *
 * @author HungLM
 */
public class listTransaction {
    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;
    
    ArrayList<Transaction> transactions;

    public listTransaction(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public listTransaction() {
        try {
            con = ConnectionDB.getConnection();
            
        } catch (Exception e) {
            System.out.println("Error");
            
        }
        
        this.getDB();
       
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    public void getDB(){
        String sql = "SELECT ProductIDs FROM Transaction";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Transaction> tmpList = new ArrayList<>();
            while(rs.next()){
                String productIDs = new String();
                Transaction transaction = new Transaction();
                
                productIDs = rs.getString("ProductIDs");
                ArrayList<String> tmpProductIDs = new ArrayList<String>(Arrays.asList(productIDs.split(",")));
                transaction.setListFactID(tmpProductIDs);
                for(String s : tmpProductIDs){
                    Fact f = new Fact(s, s);
                    transaction.getListFact().add(f);
                }
                tmpList.add(transaction);
                
            }
        this.setTransactions(tmpList);
        
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
