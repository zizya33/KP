/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;


import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class AutorizationFrame extends JFrame {
    
    JLabel output = new JLabel(" ");
    final JFrame frame = new JFrame("Авторизация"); 
    
    public void set(String tmp) {
            this.output.setText(tmp);
        }
    
    public AutorizationFrame get() {
           return this;
    }
    
    public AutorizationFrame(int size_x, int size_y) throws IOException  {
		frame.setBounds(500, 150, size_x, size_y);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#EEE8AA"));
		frame.add(panel);
		JLabel lable = new JLabel("АВТОРИЗАЦИЯ");
                lable.setSize(300,30);
		lable.setLocation(100, 40);
                lable.setForeground(Color.decode("#8B4513"));
                panel.add(lable);
                JLabel log = new JLabel("Логин");
                log.setSize(50,30);
		log.setLocation(30, 90);
                log.setForeground(Color.decode("#8B4513"));
                panel.add(log);
                final TextField login = new TextField(20);
                login.setLocation(90,90);
                login.setSize(150, 25);
                panel.add(login);
                JLabel pas = new JLabel("Пароль");
                pas.setSize(50,30);
		pas.setLocation(30, 135);
                pas.setForeground(Color.decode("#8B4513"));
                panel.add(pas);
                final JPasswordField password = new JPasswordField(20);
                password.setLocation(90,135);
                password.setSize(150, 25);
                panel.add(password);
		final JButton confirm = new JButton("Подтвердить");
		confirm.setSize(120,30);
		confirm.setLocation(90, 200);
                confirm.setBackground(Color.decode("#8B4513"));
                confirm.setForeground(Color.WHITE);
                panel.add(confirm);
                output.setSize(300,30);
		output.setLocation(70, 160);
                output.setForeground(Color.decode("#8B4513"));
                panel.add(output);   
                frame.setVisible(true);
                login.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                     
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                   
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<login.getText().length();i++)  {
                           char key = login.getText().charAt(i);
                           String k = key+"";
                           if((key<'А'||key>'Я') && (key<'а'||key>'я') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                login.setText(login.getText().replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                ex.show(get());
                           }
                           if(i>19) {
                               login.setText(login.getText().substring(0, 20));
                               break;
                           }
                        }
                }
                });
                password.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                     for(int i=0;i<String.valueOf(password.getPassword()).length();i++)  {
                           char key = String.valueOf(password.getPassword()).charAt(i);
                           String k = key+"";
                           if((key<'0'||key>'9') && (key<'А'||key>'Я') && (key<'а'||key>'я') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                password.setText(String.valueOf(password.getPassword()).replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                ex.show(get());
                           }
                           if(i>19) {
                               password.setText(String.valueOf(password.getPassword()).substring(0, 19));
                               break;
                           }
                        }}

                    @Override
                    public void keyReleased(KeyEvent e) {
                       
                }
                });
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(login.getText().length()>4 && String.valueOf(password.getPassword()).length()>4){
                                String exist  = MyClient.send("login: "+login.getText()+"|"+"password: "+String.valueOf(password.getPassword())+"|");
                                if(exist.equalsIgnoreCase("false")) set("Неверный логин или пароль");
                                else {
                                    frame.setVisible(false);
                                    if(exist.equalsIgnoreCase("admin")) {
                                        AdminFrame start_frame = new AdminFrame(300,300);
                                    }
                                    else if(exist.equalsIgnoreCase("user")) {
                                        UserMenu start_frame = new UserMenu(300,300);
                                    }
                                }
                            }
                            else {
                                set("Введите не менее 5-ти символов");
                            }
                            } catch (IOException ex) {
                            Logger.getLogger(AutorizationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
	}
}
