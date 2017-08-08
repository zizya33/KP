/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

 
public class FileWork {
   private final String fileName;
    private String action;
    private String data;
    
    public FileWork(String fileName) {
        this.fileName = fileName;
        this.action = "read";
        data = null;
    }
    
    public FileWork(String fileName, String data) {
        this.fileName = fileName;
        this.action = "write";
        this.data = data;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data=data;
    }
    
    public void work() {
        switch (action) {
            case "write":
                write();
                break;
            case "read":
                read();
                break;
        }
    }  
    
    private boolean write() {
        try(FileWriter writer = new FileWriter(fileName))
        {
            writer.append("\n" + data);
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
          return false;
        } 
        return true;
    }
    
    private void read() {
        String s = "";
        try(FileReader reader = new FileReader(fileName))
        {
            int c;
            while((c=reader.read())!=-1){ 
                char k = (char)c;
                s += k;
            } 
            reader.close();
        }
        catch(IOException ex){
        } 
        data = s;
    }     
}
