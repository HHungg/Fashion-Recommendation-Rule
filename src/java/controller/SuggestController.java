/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryData;
import model.ListRule;
import model.ProductData;
import model.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nhom10_HCSTT
 */
@Controller
public class SuggestController {
    @RequestMapping(value="/suggest")
    public String getSuggestPage(HttpServletRequest request, ModelMap mm) throws SQLException, ClassNotFoundException {
        mm.put("categoryList", CategoryData.getCategoryList() ) ;
//        mm.put("productList", ProductData.getMostProduct()) ;
        return "jsp/suggest" ;
    }
    
    
    
    @RequestMapping(value="/processSuggest", method=RequestMethod.POST)
    public String processSuggest(HttpServletRequest request, HttpServletResponse response, ModelMap mm) throws UnsupportedEncodingException, SQLException, ClassNotFoundException, IOException {
        
        request.setCharacterEncoding("utf-8");
        ArrayList<String> input = new ArrayList<String>();
        ArrayList<Products> productList = new ArrayList<Products>();
        try{
            String sex = request.getParameter("sex") ;
            String character = request.getParameter("character") ;
            String age = request.getParameter("age");
            String option = request.getParameter("option") ;
//            String job = request.getParameter("job") ;

            input.add(sex);
            input.add(character);
            input.add(age);
            input.add(option);
//            input.add(job);
            
            ArrayList<String> inputTmp = new ArrayList<>();
            for(String s: input) {
                if(!s.equals("")) 
                    inputTmp.add(s) ;
            }
            
            ArrayList<String> pIdList = this.suggestResult(inputTmp) ;
            for(String pId: pIdList) {
                productList.add(ProductData.getProductByID(pId)) ;
            }
            mm.put("productList", productList) ;
 
        }
        catch(Exception e) {
            String error = e.toString();
            System.out.println(error);
            
        }
        
        
        return "jsp/resultSuggest" ;
    }
    
    public ArrayList<String> suggestResult(ArrayList<String> input) {
        ArrayList<String> listR = new ArrayList<>();
        ListRule listRS = new ListRule();
        Reasoning r = new Reasoning();
        r.PrintRule("Rules", listRS.getList());
        ArrayList<String> suggestClothers = new ArrayList<>(r.searchProduct(listRS.getList(), input, listR));
//        System.out.println("Ket qua tu van cua he chuyen gia: ");
//        for(String s : suggestClothers)
//            System.out.println(s);
        return suggestClothers;
    }
    
//    public static void main(String[] args) {
//        ArrayList<String> input = new ArrayList<>();
//        input.add("11");
//        input.add("25");
//        
//        ArrayList<String> listR = new ArrayList<>();
//        ListRule listRS = new ListRule();
//        Reasoning r = new Reasoning();
//        r.PrintRule("Rules", listRS.getList());
//        // Kết quả gợi ý
//        ArrayList<String> suggestClothers = new ArrayList<>(r.searchProduct(listRS.getList(), input, listR));
//        System.out.println("Ket qua tu van cua he chuyen gia: ");
//        for(String s : suggestClothers)
//            System.out.println(s);
//        
//        
//        
//    }
    
}
