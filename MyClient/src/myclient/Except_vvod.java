/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;


public class Except_vvod extends My_Except {
    String messege;
    
    public Except_vvod()  {
        super();
	this.messege = "Неизвестная ошибка";
    }
    
    public Except_vvod(int code, String mes) {
        super(code);
        this.messege = mes;
    }
	
    public void set_mes(String mes)  {
        this.messege = mes;
    }
	
    public String get_mes()  {
        return this.messege;
    }
	
    public void show(AutorizationFrame frame)  {
        frame.set(this.messege);
    }
    
    public void show(AdminOrUserAdd frame)  {
        frame.set(this.messege);
    }
    
    public void show(WorkerAdd frame)  {
        frame.set(this.messege);
    }
    
    public void show(ProductAdd frame)  {
        frame.set(this.messege);
    }
    
    public void show(EditModelTest frame)  {
        frame.set(this.messege);
    }
}
