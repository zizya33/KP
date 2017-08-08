package myclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditModelTest extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    final JLabel output = new JLabel("Редактирование пароля");
    
    public void set(String tmp) {
        this.output.setText(tmp);
    }
    
    public EditModelTest get() {
        return this;
    }
    
    public EditModelTest(final String type) throws IOException
    {
        super("Администратор");
        // Создание панели
        JPanel contents = new JPanel();
        String data = MyClient.send(type);
        String[] parts = data.split("-");
        for (String string : parts) {
            dlm.add(0, string);
        }
        // Создание кнопки
        JLabel pas = new JLabel("Пароль");
        pas.setSize(50,30);
        pas.setForeground(Color.decode("#8B2252"));
        final JPasswordField password = new JPasswordField(20);
        password.setLocation(90,135);
        password.setSize(100, 25);
        final JButton Button = new JButton("ОК");
        output.setSize(300,30);
	output.setLocation(63, 160);
        output.setForeground(Color.decode("#A52A2A"));
        final JList<String> list = new JList<String>(dlm);
        list.setFixedCellWidth(100);
        list.setVisibleRowCount(4);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    Button.setEnabled(true);
                } else {
                    Button.setEnabled(false);
                }
            }
        });
        password.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {
                        for(int i=0;i<String.valueOf(password.getPassword()).length();i++)  {
                           char key = String.valueOf(password.getPassword()).charAt(i);
                           String k = key+"";
                           if((key<'0'||key>'9') && (key<'А'||key>'Я') && (key<'а'||key>'я') && (key != KeyEvent.VK_BACK_SPACE) && (key != KeyEvent.VK_SHIFT) && (key != KeyEvent.VK_CAPS_LOCK)  && (key != KeyEvent.VK_ALT_GRAPH)) {
                                password.setText(String.valueOf(password.getPassword()).replaceAll(k,""));
                                Except_vvod ex = new Except_vvod(1, "Введен недопустимый символ");
                                output.setText("Введен недопустимый символ");
                           }
                           if(i>19) {
                               password.setText(String.valueOf(password.getPassword()).substring(0, 20));
                               break;
                           }
                        }
                }
                });
        Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(password.getPassword()).length()>4 && list.getSelectedIndex()>=0){
                    try {
                        String ok  = MyClient.send("password: "+String.valueOf(password.getPassword())+"|"+"login: "+dlm.get(list.getSelectedIndex())+"|");
                    } catch (IOException ex) {
                        Logger.getLogger(EditModelTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else output.setText("Введите пароль и выберете логин");
            }
        });
        contents.add(new JScrollPane(list));
        contents.add(Button);
        contents.add(password);
        contents.add(pas);
        contents.add(output);
        setContentPane(contents);
        // Вывод окна
        setBounds(500, 150, 400, 200);
        setVisible(true);
    }
}