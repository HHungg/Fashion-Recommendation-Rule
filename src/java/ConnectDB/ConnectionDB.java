/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author HungLMr GX
 */
public class ConnectionDB {
    private   static Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saleWeb?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            
        } catch (Exception e) {
            System.out.println("Connect Database Error!");
        }
        return con;
    }
     
}