/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ConnectDB.ConnectionDB;
import model.ListRule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import model.Products;
import model.Rule;

/**
 *
 * @author HungLM
 */
public class Reasoning {

    public Connection con;
    public ResultSet rs;
    public PreparedStatement stmt;

    public String explaintion = "";
    String product = new String();
    ArrayList<String> result = new ArrayList<>();

    public Reasoning() {
        try {
            con = ConnectionDB.getConnection();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String getExplaintion() {
        return explaintion;
    }

    public void setExplaintion(String explaintion) {
        this.explaintion = explaintion;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

    public void printInput(String title, ArrayList<String> input) {
        String str = title + " ";
        for (String s : input) {
            str += s + ",";
        }
        str += "\n";
        explaintion += str;
        System.out.println(str);
    }

    // In tap luat.
    public void PrintRule(String title, ArrayList<Rule> rule) {
        String str = title + " ";
        for (Rule r : rule) {
            str += r.getLeft().toString().replace(", ", "^") + "=>" + r.getRight() + ", ";
        }
        str += " \n";
        explaintion += str;
        System.out.println(str);
    }

    public ArrayList<String> findClosedSet(ArrayList<String> input, ArrayList<Rule> listRule) {
        ArrayList<String> closedSet = new ArrayList<>(input);
        ArrayList<Rule> tmpRule = new ArrayList<>(listRule);
        for (Rule r : tmpRule) {
            r.setIsUsed(false);
        }
        boolean check = true;
        while (check) {
            check = false;
            for (Rule r : tmpRule) {
                if (r.getLeft().size() <= closedSet.size() && r.isIsUsed() == false) {
                    if (closedSet.containsAll(r.getLeft()) && !closedSet.contains(r.getRight())) {
                        closedSet.add(r.getRight());
                    }
                    r.setIsUsed(true);
                    check = true;
                }
            }
        }
        return closedSet;
    }

    //Kiem tra mot luat co du thưa hay khong.
    public boolean isSpareRule(Rule rule, ArrayList<Rule> listRule) {
        ArrayList<Rule> tmpRule = new ArrayList<>(listRule);
        tmpRule.remove(rule);
        ArrayList<String> closedSet = findClosedSet(rule.getLeft(), tmpRule);
        //ArrayList<String> allElements = findAllElements(tapLuat);
        if (closedSet.contains(rule.getRight())) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Rule> removeSpareRule(ArrayList<Rule> listRule) {
        ArrayList<Rule> optimizedListRule = new ArrayList<>(listRule);
        for (Rule r : listRule) {
            if (isSpareRule(r, listRule)) {
                optimizedListRule.remove(r);
            }
        }
        return optimizedListRule;
    }

    public ArrayList<String> forwardReasioning(ArrayList<Rule> listRule, ArrayList<String> input, ArrayList<String> result) {
//        this.giaiThich="";
        ArrayList<String> knowledgeBased = new ArrayList<>();
        knowledgeBased.addAll(input);

        ArrayList<Rule> listFitRule = processRule(listRule, knowledgeBased);
        ArrayList<Rule> track = new ArrayList<>();

//        PrintRule("Tap luat ban dau = ", tapLuat);
        printInput("Tap gia thiet ban dau = ", input);
        PrintRule("Tap luat thoa man = ", listFitRule);
        int c = 1;
        while (listFitRule.size() > 0) {
            explaintion += "Chay lan: " + c + "\n";
            System.out.println("Chay lan: " + c + "\n");
            for (Rule r : listFitRule) {
                if (!knowledgeBased.contains(r.getRight())) {
                    knowledgeBased.add(r.getRight());
                    listRule.remove(r);
                    track.add(r);
                    if (!result.contains(r.getRight())) {
                        result.add(r.getRight());
                        r.updateRank(r.getRank() + 1);
                    }
                    break;
                } else {
                    listRule.remove(r);
                    continue;
                }

            }
            listFitRule = processRule(listRule, knowledgeBased);
            printInput("Tap KB = ", knowledgeBased);

            PrintRule("Tap luat thoa man = ", listFitRule);
            c++;

        }
        PrintRule("Vet Suy dien tien = ", track);
        printInput("Tap KQ = ", result);
        return knowledgeBased;

    }

    public ArrayList<Rule> processRule(ArrayList<Rule> listRule, ArrayList<String> input) {
        ArrayList<Rule> list = new ArrayList<>();
        for (Rule r : listRule) {
            if (r.getLeft().size() <= input.size()) {
                if (input.containsAll(r.getLeft()) && !input.contains(r.getRight())) {
                    list.add(r);
                }
            }
        }
        Collections.sort(list, new Rule());
        return list;
    }

    public boolean Check(ArrayList<String> output, ArrayList<String> input) {
        for (String fact : output) {
            if (input.contains(fact)) {
                return true;
            }
        }
        return false;
    }

    // tìm sản phẩm là kết quả của việc gợi ý.
    public ArrayList<String> searchProduct(ArrayList<Rule> listRule, ArrayList<String> input, ArrayList<String> output) {
        ArrayList<Products> pList = new ArrayList<Products>();
        ArrayList<String> products = new ArrayList<>();
        ArrayList<String> info = this.forwardReasioning(listRule, input, output);
        int size = info.size();
        for(int i = 0; i < size-1; i++){
            for(int j = i+1; j<size; j++){
                if(Integer.parseInt(info.get(i)) > Integer.parseInt(info.get(j))){
                    String tmp = info.get(i);
                    info.set(i, info.get(j));
                    info.set(j, tmp); 
                }
            }
        }
        String s = "'%";
        for (String fact : info) {
            s = s + fact;
            s = s + "%";
            String sql = "SELECT PID FROM Products WHERE Mo_ta LIKE ";
            String r = s;
            sql = sql + r + "'";
            System.out.println(sql);
            try {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    products.add(rs.getString("PID"));
                }

            } catch (Exception e) {
                System.out.println("Error:" + e.toString());
            }
        }
//       String sql =  "SELECT PID FROM Products WHERE Mo_ta LIKE ";
//       sql = sql + s + "'";
//       try {
//            stmt = con.prepareStatement(sql);
//            rs = stmt.executeQuery();
//           
//            while(rs.next()){
//                products.add(rs.getString("PID"));
//            }
//          
//        } catch (Exception e) {
//            System.out.println("Error:"+e.toString());
//        }
        if(products.size() > 3){
            for(int i = 3; i < products.size(); i++)
                products.remove(i);
        }
        return products;
    }
}
