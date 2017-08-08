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

public class ProductAdd extends JFrame {
    
    JLabel output = new JLabel(" ");
    final JFrame frame = new JFrame("Администратор");
    
    public void set(String tmp) {
        this.output.setText(tmp);
    }
    
    public ProductAdd get() {
        return this;
    }
    
    public ProductAdd(int size_x, int size_y, final String type) throws IOException  {
		frame.setBounds(500, 150, size_x, size_y);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#EEE8AA"));
		frame.add(panel);
                JLabel lable = new JLabel("ДОБАВЛЕНИЕ");
                lable.setSize(300,30);
		lable.setLocation(100, 20);
                lable.setForeground(Color.decode("#8B4513"));
                panel.add(lable);
                JLabel name = new JLabel("Название");
                name.setSize(60,30);
		name.setLocation(30, 90);
                name.setForeground(Color.decode("#8B4513"));
                panel.add(name);
                final TextField nameInput = new TextField(20);
                nameInput.setLocation(130,90);
                nameInput.setSize(150, 25);
                panel.add(nameInput);
                JLabel prop = new JLabel("Главный показатель");
                prop.setSize(100,30);
		prop.setLocation(30, 135);
                prop.setForeground(Color.decode("#8B4513"));
                panel.add(prop);
                final TextField property = new TextField(20);
                property.setLocation(130,135);
                property.setSize(150, 25);
                panel.add(property);
                JLabel b_val = new JLabel("Значение");
                b_val.setSize(60,30);
		b_val.setLocation(30, 180);
                b_val.setForeground(Color.decode("#8B4513"));
                panel.add(b_val);
                final TextField b_value = new TextField(20);
                b_value.setLocation(130,180);
                b_value.setSize(150, 25);
                panel.add(b_value);
                final JButton confirm = new JButton("Добавить");
		confirm.setSize(120,30);
		confirm.setLocation(20, 225);
                confirm.setBackground(Color.decode("#8B4513"));
                confirm.setForeground(Color.WHITE);
                panel.add(confirm);
                final JButton cont = new JButton("Продолжить");
		cont.setSize(120,30);
		cont.setLocation(150, 225);
                cont.setBackground(Color.decode("#8B4513"));
                cont.setForeground(Color.WHITE);
                panel.add(cont);
                output.setSize(300,30);
		output.setLocation(70, 200);
                output.setForeground(Color.decode("#A52A2A"));
                panel.add(output); 
                nameInput.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<nameInput.getText().length();i++)  {
                           char key = nameInput.getText().charAt(i);
                           String k = key+"";
                           if((key<'А'||key>'Я') && (key<'а'||key>'я') && (key<'0'||key>'9')&& (key!=' ') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                nameInput.setText(nameInput.getText().replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                //ex.show(get());
                           }
                           if(i>19) {
                               nameInput.setText(nameInput.getText().substring(0, 20));
                               break;
                           } 
                        }
                }
                });
                
                property.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<property.getText().length();i++)  {
                           char key = property.getText().charAt(i);
                           String k = key+"";
                           if((key<'А'||key>'Я') && (key<'0'||key>'9') && (key<'а'||key>'я')&& (key!=' ') && (key!=',') && (key!='.')&& (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                property.setText(property.getText().replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                //ex.show(get());
                           }
                           if(i>19) {
                               property.setText(property.getText().substring(0, 20));
                               break;
                           } 
                        }
                }
                });
                
                b_value.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<b_value.getText().length();i++)  {
                           char key = b_value.getText().charAt(i);
                           String k = key+"";
                           if( (key<'0'||key>'9') && (key!='.') && (key!=' ') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                b_value.setText(b_value.getText().replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                //ex.show(get());
                           }
                           if(i>19) {
                               b_value.setText(b_value.getText().substring(0, 20));
                               break;
                           } 
                        }
                }
                });
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(nameInput.getText().length()>2){
                                nameInput.setText(nameInput.getText().toLowerCase());
                                property.setText(property.getText().toLowerCase());
//                                if( Character.isLowerCase(nameInput.getText().charAt(0))){
//                                    String c = Character.toUpperCase(nameInput.getText().charAt(0))+"";
//                                    nameInput.setText(c+nameInput.getText().substring(1));
//                                }
                                String exist  = MyClient.send("addProduct|name: "+nameInput.getText()+"|property: "+property.getText()+"|b_value: "+b_value.getText()+"|");
                                if(exist.equalsIgnoreCase("false")) set("Такой товар есть");
                                else if(exist.equalsIgnoreCase("true")) set("Добавлен");
                            }
                            else {
                                set("Введите не менее 2-х символов");
                            }
                            
                            
                            } catch (IOException ex) {
                            Logger.getLogger(AutorizationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                cont.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ShowFrame start_frame = new ShowFrame();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
