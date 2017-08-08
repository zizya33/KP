/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static myserver.MyServer.count;

public class Session implements Runnable{
    private Socket socket;
    private InputStream input = null;
    private OutputStream output = null;
    
    Session(Socket socket) throws IOException { 
            this.socket = socket;
            this.input = socket.getInputStream();
            this.output = socket.getOutputStream();
    }
    
    @Override
    public void run() {
        MyServer.count++;
        System.out.println("Сессия начата. Кол-во подключений: "+count);
        BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(input));
        String message = null;
        try {
            message = buffered_reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        int situation_code = define_situation(message);
        switch (situation_code) {
            case 1:
                {
                    String login = get(message, "login: ");
                    String password = get(message, "password: ");
                    String exist = "false";
                    try {
                        exist = DBConnection.isExist("SELECT status FROM data.user WHERE login='"+login+"' AND password = '" + password + "'", "status");
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(exist);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 2:
                {
                    String login = get(message, "login: ");
                    String password = get(message, "password: ");
                    String status = get(message, "status: ");
                    String exist = "false";
                    String request = "false";
                    try {
                        exist = DBConnection.isExist("SELECT login FROM data.user WHERE login='"+login+"'", "login");
                        if(exist.equalsIgnoreCase("false")){
                            request = DBConnection.execute("INSERT INTO data.user (login, password, status) VALUES ('"+login+"', '"+password+"', '"+status+"')");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 3:
                {
                    String surname = get(message, "surname: ");
                    String name = get(message, "_name: ");
                    String exist = "false";
                    String request = "false";
                    try {
                        exist = DBConnection.isExist("SELECT surname FROM data.worker WHERE surname='"+surname+"' AND name='"+name+"'", "surname");
                        if(exist.equalsIgnoreCase("false")){
                            request = DBConnection.execute("INSERT INTO data.worker (surname, name) VALUES ('"+surname+"', '"+name+"')");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 4:
                {
                    String name = get(message, "name: ");
                    String property = get(message, "property: ");
                    String b_value = get(message, "b_value: ");
                    String exist = "false";
                    String request1 = "false";
                    String request = "false";
                    try {
                        exist = DBConnection.isExist("SELECT base_value FROM data.properties WHERE property='"+property+"' and base_value='"+b_value+"';", "base_value");
                        if(exist.equalsIgnoreCase("false")){
                            request1 = DBConnection.execute("INSERT INTO data.properties (property, base_value) VALUES ('"+property+"', '"+b_value+"')");
                         
                        }
                        
                        exist = DBConnection.isExist("SELECT name FROM data.product WHERE name='"+name+"'", "name");
                        if(exist.equalsIgnoreCase("false")){
                            request = DBConnection.execute("INSERT INTO data.product (name, id_property) VALUES ('"+name+"', (SELECT id_property from data.properties WHERE base_value='"+b_value+"' and property='"+property+"')) ;");
                        } 
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 5:
                {
                    String request = "false";
                    try {
                        request = DBConnection.executeQ("SELECT login FROM user WHERE status='admin'", "login", null);
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 6:
                {
                    String request = "false";
                    try {
                        request = DBConnection.executeQ("SELECT login FROM user WHERE status='user'", "login", null);
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 7:
                {
                    String request = "false";
                    try {
                        request = DBConnection.executeQ("SELECT * FROM worker", "surname", "name");
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 8:
                {
                    String request = "false";
                    try {
                        request = DBConnection.executeQ("SELECT name FROM data.product", "name", null);
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 9:
                {
                    String type = get(message, "type: ");
                    if(type.equalsIgnoreCase("admin")) type = "user";
                    String name = get(message, "name: ");
                    String request = "false";
                    String sql = null;
                    if(type.equalsIgnoreCase("user")) sql = "DELETE FROM data."+type+ " WHERE login='"+name+"';";
                    else if(type.equalsIgnoreCase("worker")) {
                        int to = name.indexOf(" ");
                        name = name.substring(0, to);
                        sql = "DELETE FROM "+type+ " WHERE surname='"+name+"';";
                    }
                    else if(type.equalsIgnoreCase("product")) sql = "DELETE FROM data.product WHERE name='"+name+"';";
                    try {
                        request = DBConnection.execute(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       try {
                        send(request);
                    } catch (IOException ex) {
                        Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 10:
            {
                
                String result="";
            try {
                
                
                ResultSet rs = DBConnection.executeSet("SELECT data.product.name, data.properties.property, data.properties.base_value, data.product.this_value FROM data.product inner join data.properties on data.product.id_property=data.properties.id_property;");
                while(rs.next()){
            String name="";
            
            name = rs.getString("name");
            String tv=rs.getString("this_value");
            String pr = rs.getString("property");
            
            String bv=rs.getString("base_value");
            
            result = result+name+"-";
            
            String RES="";
            float r=0;
            if (tv==null) r=0;
            else {
            r=Float.parseFloat(tv)/Float.parseFloat(bv);
            
            }
            RES=Float.toString(r);
            
            DBConnection.execute("UPDATE data.product SET rel_index="+r+" WHERE name='"+name+"';");
            result = result+pr+"-";
            result = result+RES+"--";

        }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                try {
                    send(result);
                } catch (IOException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            }
                
            
            case 11:
            {
                String password = get(message, "password: ");
            String login = get(message, "login: ");
            try {
                DBConnection.execute("UPDATE data.user SET password='"+password+"' WHERE login='"+login+"'");
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                send("ok");
            } catch (IOException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }break;
            }
           
//            case 12:
//            {
//                
//                String result="";
//                //String bval="false";
//            try {
//                
//                
//                //res = DBConnection.CountRelInd();
//                //ResultSet rs = DBConnection.executeSet("SELECT* FROM data.product");
//                ResultSet rs = DBConnection.executeSet("SELECT data.product.name, data.properties.property, data.product.rel_index, data.product.price, data.product.quantity  FROM data.product inner join data.properties on data.product.id_property=data.properties.id_property;");
//                //ResultSet rs2 = DBConnection.executeSet("SELECT* FROM data.properties");
//                
//                float sum=0;
//        while(rs.next()){
//            String name="";
//            
//            name = rs.getString("name");
//            String r_i=rs.getString("rel_index");
//            String pr = rs.getString("property");
//            
//            //new
//            String cena=rs.getString("price");
//            String qua=rs.getString("quantity");
//            
//            result = result+name+"-";
//            
//            String RES="";
//            float r=0;
//            //bval = DBConnection.isExist("SELECT base_value FROM data.properties WHERE property='"+pr+"';", "base_value");
//            if (r_i==null) r=0;
//            //RES="недостаточно данных";
//            else {
//            r=Float.parseFloat(r_i)*Integer.parseInt(cena)*Integer.parseInt(qua);
//            sum+=r;
//            }
//            
//            RES=Float.toString(r);
//            
//            //Statement statement2 = connection.createStatement();
//            DBConnection.execute("UPDATE data.product SET rel_index="+r+" WHERE name='"+name+"';");
//                
//            //name = rs.getString("rel_index");
//            result = result+pr+"-";
//            result = result+RES+"--";
//
//        }
//                
//                
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
//            }
//                
//                
//                try {
//                    send(result);
//                } catch (IOException ex) {
//                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
//                }   break;
//            }
            
            case 13:
            {
               String request = "false";
            try {
                request = DBConnection.getAllProducts();
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                send(request);
            } catch (IOException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            } break; 
            
            }
            case 14: 
            {
                
                String data = get(message, "update|");
                String [] r = data.split("-");
                //String req="";
                try {
                //req="UPDATE data.product SET "+rows[0]+"='"+rows[1]+"' WHERE name='"+rows[3]+"'";  
                DBConnection.execute("UPDATE data.product SET "+r[0]+"='"+r[1]+"' WHERE name='"+r[3]+"'");
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                send("ok");
            } catch (IOException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            } break;
            }
            case 15:
            {
                
                String result="";
                //String bval="false";
            try {
                
                
                
                
                //res = DBConnection.CountRelInd();
                //ResultSet rs = DBConnection.executeSet("SELECT* FROM data.product");
                ResultSet rs = DBConnection.executeSet("SELECT data.product.name, data.properties.property, data.properties.base_value, data.product.this_value FROM data.product inner join data.properties on data.product.id_property=data.properties.id_property;");
                //ResultSet rs2 = DBConnection.executeSet("SELECT* FROM data.properties");
        while(rs.next()){
            String pr = rs.getString("property");
            
            if (pr.equals("ходимость, тыс км")) {
            String name="";
            
            name = rs.getString("name");
            String tv=rs.getString("this_value");
            //String pr = rs.getString("property");
            
            //new
            String bv=rs.getString("base_value");
            
            result = result+name+"-";
            
            String RES="";
            float r=0;
            //bval = DBConnection.isExist("SELECT base_value FROM data.properties WHERE property='"+pr+"';", "base_value");
            if (tv==null) r=0;
            //RES="недостаточно данных";
            else {
            r=Float.parseFloat(tv)/Float.parseFloat(bv);
            
            }
            RES=Float.toString(r);
            
            //Statement statement2 = connection.createStatement();
            DBConnection.execute("UPDATE data.product SET rel_index="+r+" WHERE name='"+name+"';");
                
            //name = rs.getString("rel_index");
            result = result+pr+"-";
            result = result+RES+"--";

        }
        }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                try {
                    send(result);
                } catch (IOException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            }
            default:
                break;
        }
        
       MyServer.count--;
        System.out.println("Сессия закончена. Кол-во подключений: "+count);
    }    
    public String get(String message, String sub){
        int from = message.indexOf(sub)+sub.length();
        int to = message.indexOf("|", from);
        return message.substring(from, to);
    }
    
    public int define_situation(String message){
        if(message!=null) {
            if(message.startsWith("login: "))   return 1;
            else if(message.startsWith("registration|"))   return 2;
            else if(message.startsWith("addWorker|"))   return 3;
            else if(message.startsWith("addProduct|"))   return 4;
            else if(message.startsWith("admin"))   return 5;
            else if(message.startsWith("user"))   return 6;
            else if(message.startsWith("worker"))   return 7;
            else if(message.startsWith("product"))   return 8;
            else if(message.startsWith("delete|"))   return 9;
            else if(message.startsWith("otn_ind"))   return 10;
            else if(message.startsWith("password"))   return 11;
            //else if(message.startsWith("commmmm_ind"))   return 12;
            else if(message.startsWith("prod|"))   return 13;
            else if(message.startsWith("update|"))   return 14;
            else if(message.startsWith("otnnn_ind"))   return 15;
        }
        return 0;
    }
    
    boolean send(String message) throws IOException {
        try {
            output.write((message+"\r").getBytes());
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }
}