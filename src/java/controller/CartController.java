/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.ProductData;
import model.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Nhom10_HCSTT
 */
@Controller
@RequestMapping(value="/cart")
public class CartController {
    
    @RequestMapping(value="/detail")
    public String getCartDetail(HttpServletRequest request, ModelMap mm) throws SQLException, ClassNotFoundException {
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null)
            cart = new Cart();
        mm.put("cart", cart) ;
        
        ArrayList<String> pIDs = new ArrayList<String>();
        for(Map.Entry<String, Item> list : cart.getCartItems().entrySet()) {
            pIDs.add(list.getValue().getProduct().getPID()) ;
        }
        
        ArrayList<Products> productList = new ArrayList<Products>();
        
        try {
            // Lay ra pids tu goi y
            ArrayList<String> pIdList = AI_Fashion.suggestFromCart(pIDs) ;

            // lay cac product goi y tu pIdList duoc goi y
            for(String pId: pIdList) {
                    productList.add(ProductData.getProductByID(pId)) ;
            }
            mm.put("productList", productList) ;
        }
        catch(Exception e) {
            mm.put("productList", productList) ;
        }
        return "jsp/cart" ;
    }
    @RequestMapping(value="/add")
    public String addToCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm, @RequestParam String productID) throws IOException {
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null)
            cart = new Cart();
        
        try {
            Products product = ProductData.getProductByID(productID);
            if (cart.getCartItems().containsKey(productID)) {
                cart.addToCart(productID, new Item(product, cart.getCartItems().get(productID).getQuantity()));
            } else {
                cart.addToCart(productID, new Item(product, 1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("cart", cart);
        mm.put("cart", cart) ;
        response.sendRedirect(request.getContextPath()+"/home");
        return "jsp/index" ;
    }
    
    @RequestMapping(value="/remove")
    public String removeToCart(HttpServletRequest request, HttpServletResponse response, ModelMap mm, @RequestParam String productID) throws IOException {
        
        HttpSession session = request.getSession() ;
        Cart cart = (Cart) session.getAttribute("cart") ;
        
        try {
            Products product = ProductData.getProductByID(productID) ;
            cart.removeToCart(productID);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        session.setAttribute("cart", cart);
        mm.put("cart", cart) ;
        response.sendRedirect(request.getContextPath()+"/home");
        
        return "jsp/index" ;
    }
    
    
    
}
