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
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author HungLM
 */
public class ListRule {
    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;
    
    ArrayList<Rule> list;
    
    /**
     * Creates new form CongThuc
     */
    public ListRule() {
        try {
            con = ConnectionDB.getConnection();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        this.getDB();
       
    }

    public ListRule(ArrayList<Rule> list) {
        this.list = list;
    }

    public ArrayList<Rule> getList() {
        return list;
    }

    public void setList(ArrayList<Rule> list) {
        this.list = list;
    }

    

   
    
    public void getDB(){
        String sql = "SELECT * FROM Luat;";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Rule> tmpList = new ArrayList<>();
            while(rs.next()){
                String left = new String();
                Rule tmpRule = new Rule();
                
                tmpRule.setRuleID(Integer.parseInt(rs.getString("ID")));
             
                left = rs.getString("Ve_trai").replace("[", "").replace("]", "");
                left = left.replace(" ","");
                ArrayList<String> leftRule = new ArrayList<String>(Arrays.asList(left.split(",")));
                tmpRule.setLeft(leftRule);
                tmpList.add(tmpRule);
                
                String right = new String();
                right = right = rs.getString("Ve_phai").replace("[", "").replace("]", "");
                right = right.replaceAll(" ","");
                tmpRule.setRight(right);
                tmpRule.setRank(Integer.parseInt(rs.getString("Do_tin_cay")));
                tmpRule.setExplaintion(rs.getString("Giai_thich"));
                
            }
        this.setList(tmpList);
        
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void addRule(String ID, String left, String right, String explaintion, int rank) throws SQLException{
        Connection con=null;
        con=ConnectionDB.getConnection();
        String query=" insert into Luat values(N"+left+"',N'"+right+"',N'"+explaintion+"',"+rank+")";
        PreparedStatement prepare = con.prepareStatement(query);
        prepare.executeUpdate();
        
    }
    public void deleteRule(String ID ) throws SQLException{
       Connection con=null;
       con= ConnectionDB.getConnection();
       String query="Delete From Luat Where [ID]=N'"+ID+"'";
       PreparedStatement pre=con.prepareStatement(query);
       pre.executeUpdate();  
   }
}
