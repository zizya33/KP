/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Liza
 */
public class DBConnection {
    public static boolean isAdminExist() throws SQLException{
        Statement statement, createNewAdmin;
        Connection connection = null;
        
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "Happyness1805");
        }
        catch(ClassNotFoundException | SQLException e)  {
            
            System.out.println(e);
            System.exit(-1);
        }
        statement = connection.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT * FROM  data.user WHERE status='admin'");
        if(!result_set.next())  {
            createNewAdmin = connection.createStatement();
            createNewAdmin.execute("INSERT INTO data.user (login, password, status) VALUES ('админ', 'админ', 'admin')");
        }
        return true;
    }

 public static String isExist(String sql, String field) throws SQLException{
        Statement statement;
        Connection connection = null;
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Happyness1805");
        }
        catch(ClassNotFoundException e)  {
            System.exit(-1);
        }
        statement = connection.createStatement();
        ResultSet result_set = statement.executeQuery(sql);
        if(!result_set.next())  {
            return "false";
        }
        else return result_set.getString(field);
    }
 
    public static String execute(String sql) throws SQLException{
        Statement statement;
        Connection connection = null;
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Happyness1805");
        }
        catch(ClassNotFoundException e)  {
            System.exit(-1);
        }
        statement = connection.createStatement();
        statement.execute(sql);
        return "true";
    }
    
    public static String executeQ(String sql, String attr1, String attr2) throws SQLException{
        Statement statement;
        Connection connection = null;
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "Happyness1805");
        }
        catch(ClassNotFoundException e)  {
            System.exit(-1);
        }
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        String result = "";
        while(rs.next()){
            result = result+rs.getString(attr1);
            if(attr2!=null) result = result+" "+rs.getString(attr2);
            result = result + "-";
        }
        if (!result.equals("")) return result.substring(0, result.length()-1);
        else return result;
    }
    public static String getAllProducts() throws SQLException{
        Statement statement;
        Connection connection = null;
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "Happyness1805");
        }
        catch(ClassNotFoundException e)  {
            System.out.println(e);
            System.exit(-1);
        }
        statement = connection.createStatement();
        //Statement statement1 = connection.createStatement();
        String result = "";
        ResultSet rs = statement.executeQuery("SELECT data.product.name, data.properties.property, data.properties.base_value, data.product.this_value, data.product.price, data.product.quantity FROM data.product inner join data.properties on data.product.id_property=data.properties.id_property;");
        //ResultSet rs2 = statement1.executeQuery("SELECT* FROM data.property");
        while(rs.next()){
            String name="";
            
            name = rs.getString("name");
            result = result+name+"-";
            name = rs.getString("property");
            result = result+name+"-";
//            Statement statement1 = connection.createStatement();
//            ResultSet rs2 = statement1.executeQuery("SELECT* FROM data.property");
//            while (rs2.next()){
//                String pr="";
//                pr=rs2.getString("property");
//                if (pr.equals(rs.getString("property"))) {
//                    result = result+rs2.getString("base_value")+"-";
//                    break;
//                }
//                
//            } 
            name = rs.getString("this_value");
            result = result+name+"-";
            name = rs.getString("price");
            result = result+name+"-";
            name = rs.getString("quantity");
            result = result+name+"--";
        }
   
        return result.substring(0, result.length()-2);
    }
    
    public static ResultSet executeSet(String sql) throws SQLException{
        Statement statement;
        Connection connection = null;
        try  {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "Happyness1805");
        }
        catch(ClassNotFoundException e)  {
            System.exit(-1);
        }
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
//        String result = "";
//        while(rs.next()){
//            result = result+rs.getString(attr1);
//            if(attr2!=null) result = result+" "+rs.getString(attr2);
//            result = result + "-";
//        }
//        if (!result.equals("")) return result.substring(0, result.length()-1);
       // else 
        return rs;
    }
    
}
