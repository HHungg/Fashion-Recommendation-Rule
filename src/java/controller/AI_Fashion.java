/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author HungLM
 */
public class AI_Fashion {

    /**
     * @param args the command line arguments
     */
    // hàm đưa ra gợi ý các sản phẩm từ đơn hàng
    public static ArrayList<String> suggestFromCart(ArrayList<String> productIDs) throws SQLException {

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
            }
            // thêm dòng này là tự động thêm luật dựa vào đợn hàng.
//            s.addRule();
        }

//        ArrayList<String> produntIDs = new ArrayList<>();
        ArrayList<String> results = new ArrayList<>();
//        productIDs.add("1");  // TRUYEN VAO TU THAM SO NEN KO CAN ADD
//        productIDs.add("2");
        Reasoning reasoning = new Reasoning();
        reasoning.PrintRule("Rules", rules);
        ArrayList<String> suggestProducts = new ArrayList<>(reasoning.forwardReasioning(rules, productIDs, results));

        return suggestProducts;
    }

    public static void main(String[] args) throws SQLException {

//
//        listTransaction listT = new listTransaction();
//        Apriori apriori = new Apriori();
//        apriori.setMinSup(0.5);
//        apriori.setMinConf(0.7);
//        apriori.setListTransaction(listT.getTransactions());
//        ArrayList<Suggestion> suggestions = new ArrayList<>(apriori.generate_association_rules(apriori.frequent_itemsets(listT.getTransactions())));
//        System.out.println("Number of suggestion "+suggestions.size());
//        for(Suggestion s : suggestions){
//            System.out.println(s.print());
//            s.addRule();
//        }
//        ListRule listRS = new ListRule();
//        Reasoning r = new Reasoning();
//        r.PrintRule("Tap luat ban dau", listRS.getList());
//        r.PrintRule("Tap luat toi uu",r.removeSpareRule(listRS.getList()));
        // Gợi ý từ đơn hàng
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
            }
            // thêm dòng này là tự động thêm luật dựa vào đợn hàng.
//            s.addRule();    
        }
        System.out.println(rules.size());
        ArrayList<String> produntIDs = new ArrayList<>();
        ArrayList<String> results = new ArrayList<>();
        produntIDs.add("101");
        Reasoning reasoning = new Reasoning();
        reasoning.PrintRule("Rules", rules);
        ArrayList<String> suggestProducts = new ArrayList<>(reasoning.forwardReasioning(rules, produntIDs, results));
        System.out.println("Ket qua goi y cua data mining: ");
        for (String s : suggestProducts) {
            System.out.println(s);
        }

//        ArrayList<String> input = new ArrayList<>();
//        ArrayList<String> listR = new ArrayList<>();
//        input.add("1");
//        input.add("2");
//        ListRule listRS = new ListRule();
//        Reasoning r = new Reasoning();
//        r.PrintRule("Rules", listRS.getList());
//        ArrayList<String> suggestClothers = new ArrayList<>(r.searchProduct(listRS.getList(), input, listR));
//        System.out.println("Ket qua tu van cua he chuyen gia: ");
//        for(String s : suggestClothers)
//            System.out.println(s);
    }

}
