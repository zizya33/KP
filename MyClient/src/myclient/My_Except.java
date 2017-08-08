/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

public class My_Except {
	int code;
        
	public My_Except() {
            this.code = 0;
        }
	
        public My_Except(int code) {
            this.code = code;
        }
        
	public void set_code(int code)  {
            this.code = code;
        }
        
	public int get_code()  {
            return this.code;
        }
}
