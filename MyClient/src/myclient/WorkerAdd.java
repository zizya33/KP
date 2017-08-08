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

public class WorkerAdd  extends JFrame {
    
    JLabel output = new JLabel(" ");
    final JFrame frame = new JFrame("Администратор");
    
    public void set(String tmp) {
        this.output.setText(tmp);
    }
    
    public WorkerAdd get() {
        return this;
    }
    
    public WorkerAdd(int size_x, int size_y, final String type) throws IOException  {
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
                JLabel surname = new JLabel("Фамилия");
                surname.setSize(60,30);
		surname.setLocation(30, 90);
                surname.setForeground(Color.decode("#8B4513"));
                panel.add(surname);
                final TextField surnameInput = new TextField(20);
                surnameInput.setLocation(90,90);
                surnameInput.setSize(150, 25);
                panel.add(surnameInput);
                JLabel name = new JLabel("Имя");
                name.setSize(50,30);
		name.setLocation(30, 135);
                name.setForeground(Color.decode("#8B4513"));
                panel.add(name);
                final TextField nameInput = new TextField(20);
                nameInput.setLocation(90,135);
                nameInput.setSize(150, 25);
                panel.add(nameInput);
		final JButton confirm = new JButton("Добавить");
		confirm.setSize(120,30);
		confirm.setLocation(90, 200);
                confirm.setBackground(Color.decode("#8B4513"));
                confirm.setForeground(Color.WHITE);
                panel.add(confirm);
                output.setSize(300,30);
		output.setLocation(63, 160);
                output.setForeground(Color.decode("#A52A2A"));
                panel.add(output); 
                surnameInput.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<surnameInput.getText().length();i++)  {
                           char key = surnameInput.getText().charAt(i);
                           String k = key+"";
                           if((key<'А'||key>'Я') && (key<'а'||key>'я') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                surnameInput.setText(surnameInput.getText().replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                //ex.show(get());
                           }
                           if(i>19) {
                               surnameInput.setText(surnameInput.getText().substring(0, 20));
                               break;
                           } 
                        }
                }
                });
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
                           if((key<'А'||key>'Я') && (key<'а'||key>'я') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
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
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(nameInput.getText().length()>2 && surnameInput.getText().length()>2){
                                nameInput.setText(nameInput.getText().toLowerCase());
                                surnameInput.setText(surnameInput.getText().toLowerCase());
                                if( Character.isLowerCase(nameInput.getText().charAt(0))){
                                    String c = Character.toUpperCase(nameInput.getText().charAt(0))+"";
                                    nameInput.setText(c+nameInput.getText().substring(1));
                                }
                                if( Character.isLowerCase(surnameInput.getText().charAt(0))){
                                    String c = Character.toUpperCase(surnameInput.getText().charAt(0))+"";
                                    surnameInput.setText(c+surnameInput.getText().substring(1));
                                }
                                String exist  = MyClient.send("addWorker|surname: "+surnameInput.getText()+"|"+"_name: "+nameInput.getText()+"|");
                                if(exist.equalsIgnoreCase("false")) set("Такой работник есть");
                                else if(exist.equalsIgnoreCase("true")) set("Зарегистрирован");
                            }
                            else {
                                set("Введите не менее 2-х символов");
                            }
                            } catch (IOException ex) {
                            Logger.getLogger(AutorizationFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
