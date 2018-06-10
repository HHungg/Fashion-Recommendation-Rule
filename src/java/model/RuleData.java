/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Apriori;
import controller.Reasoning;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import otherAddOn.DbConnect;

/**
 *
 * @author HungLM <20142078@student.hust.edu.vn>
 */
public class RuleData {
    
    
    public RuleData() {
        
    }
    //show list luat trong DB
    public static ArrayList<Rule> getRuleList() throws SQLException, ClassNotFoundException {
        ArrayList<Rule> ruleList = new ArrayList<Rule>();
        DbConnect connect = new DbConnect();
        String query = "SELECT ID, Ve_trai, Ve_phai, Do_tin_cay FROM luat";

        try {
            PreparedStatement prSt = (PreparedStatement) connect.con.prepareStatement(query);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                Rule rule = new Rule();
                rule.setRuleID(rs.getInt("ID"));
                String left = new String();
                left = rs.getString("Ve_trai").replace("[", "").replace("]", "");
                left = left.replace(" ", "");
                ArrayList<String> leftRule = new ArrayList<String>(Arrays.asList(left.split(",")));
                rule.setLeft(leftRule);
                rule.setRight(rs.getNString("Ve_phai"));
                rule.setRank(rs.getInt("Do_tin_cay"));

                ruleList.add(rule);
            }
            connect.con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage().toString());
        }
        return ruleList;
    }

    public static String updateRule(int ID, String left,String right, int rank) throws SQLException {

        try {

            DbConnect connect = new DbConnect();
            String query = "UPDATE luat SET Ve_trai= '" + left + "', Ve_phai = '" + right + "' , Do_tin_cay = '" + rank + "' WHERE (ID='" + ID + "')";
            PreparedStatement ps = (PreparedStatement) connect.con.prepareStatement(query);

            int rs = connect.st.executeUpdate(query);
            connect.con.close();
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String addRule(String left, String right, int rank) throws SQLException {
        try {
            DbConnect connect = new DbConnect();

            String query = "INSERT INTO luat( Ve_trai, Ve_phai, Do_tin_cay) VALUE( '" + left + "', '" + right + "' ," + rank + ")";
            System.out.println(query);

            PreparedStatement ps = connect.con.prepareStatement(query);
            int rs = connect.st.executeUpdate(query);
            connect.con.close();
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String deleteRule(int ID) throws SQLException {
        try {

            DbConnect connect = new DbConnect();
            String query = "DELETE FROM luat WHERE ID = " + ID;
            PreparedStatement ps = connect.con.prepareStatement(query);
            int rs = connect.st.executeUpdate(query);
            connect.con.close();
            return null;

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Kiem tra mot luat co du thua hay khong => return 1: luat du thua, 0: luat khong du thua
    public boolean isSpareRule(Rule rule) throws SQLException, ClassNotFoundException {
        Reasoning reasoning = new Reasoning();
        return reasoning.isSpareRule(rule, this.getRuleList());
    }

    //Thay the tap luat trong DB bang tap luat moi khong du thua
    public ArrayList<Rule> removeSpareRule() throws SQLException, ClassNotFoundException {
        ArrayList<Rule> ruleList = new ArrayList<>();
        Reasoning reasoning = new Reasoning();
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules = this.getRuleList();
        ruleList = reasoning.removeSpareRule(rules);
        for (Rule r : rules) {
            this.deleteRule(r.getRuleID());
        }
        for (Rule r : ruleList) {
            this.addRule(r.getLeft().toString(), r.getRight(), r.getRank());
        }
        return ruleList;
    }

    //Tu dong them luat vao DB
    public static ArrayList<Rule> autoAddRule() throws SQLException {
        listTransaction listT = new listTransaction();
        Apriori apriori = new Apriori();
        apriori.setMinSup(0.5);
        apriori.setMinConf(0.7);
        apriori.setListTransaction(listT.getTransactions());
        ArrayList<Suggestion> suggestions = new ArrayList<>(apriori.generate_association_rules(apriori.frequent_itemsets(listT.getTransactions())));
        ArrayList<Rule> rules = new ArrayList<>();
        for (Suggestion s : suggestions) {
            if (s.getRight().size() == 1) {
                rules.add(s.toRule());
                s.addRule();
            }
        }

        return rules;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
//        ArrayList<String> vt = new ArrayList<String>();
//        vt.add("1");
//        vt.add("2");
//        Rule r = new Rule(vt,"3",false, 1,"Null",21) ;
//        String error = addRule(r) ;
//        ArrayList<Rule> rList = getRuleList();
//        System.out.println(rList.get(1).getRight());

        String error = addRule("2,3,5", "7", 0) ;
        System.out.println(error);
        
    }
}
