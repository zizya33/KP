/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {
    
    static private InputStream input = null;
    static private OutputStream output = null;
    static private Socket client = null;
    static private int PORT_NUM=0;
    
    public static void main(String[] args) throws IOException {
        AutorizationFrame start_frame = new AutorizationFrame(300,300);
        
        
       
        String result="";
        try(FileReader reader = new FileReader("C:\\Users\\Liza\\Documents\\NetBeansProjects\\MyServer\\config.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                result += (char)c;
            }
            PORT_NUM=Integer.parseInt(result);
        }
        catch(IOException ex){

            PORT_NUM=2525;
        }

        
        try {
            client = new Socket("127.0.0.1",PORT_NUM);
            init();
            client.close();
        }
        catch(Exception e)  {
            start_frame.set("Сервер не запущен");
        }
    }
    
    public static void init() throws IOException {
        input = client.getInputStream();
        output = client.getOutputStream();
    }
    
    static String send(String message) throws IOException {
        try {
            client = new Socket("127.0.0.1",PORT_NUM);
            init();
            output.write((message+"\r").getBytes());
            BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(input));
            String response = null;
            try {
                response = buffered_reader.readLine();
                return response;
            } catch (IOException ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            client.close();
        }
        catch(Exception e) {
            return null;
        }
        return null;
    }    
}

