package myclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserSet extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    private DefaultListModel<String> dlm1 = new DefaultListModel<String>();
    JLabel output = new JLabel(" ");
    
    public UserSet(final String filials, final int count) throws IOException
    {
        super("ПОЛЬЗОВАТЕЛЬ");
        // Создание панели
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contents = new JPanel();
        String data = MyClient.send("worker");
        String[] parts = data.split("-");
        dlm.add(0, "---------------------------------------------");
        dlm1.add(0, "---------------------------------------------");
        for (String string : parts) {
            dlm.addElement(string);
        }
        // Создание кнопки
        JLabel lable = new JLabel("Выберете пользовтелей");
        contents.add(lable);
        lable.setLocation(10, 10);
        final JButton addButton = new JButton("Добавить");
        addButton.setEnabled(false);
        final JButton delButton = new JButton("Удалить");
        delButton.setEnabled(false);
        final JButton ok = new JButton("Подтвердить");
        ok.setEnabled(false);
        final JList<String> list = new JList<String>(dlm);
        final JList<String> list1 = new JList<String>(dlm1);
        list.setPreferredSize( new Dimension(180, 180));
        list1.setPreferredSize( new Dimension(180, 180));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() >= 1) {
                    addButton.setEnabled(true);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = list.getSelectedValue();
                dlm.remove(list.getSelectedIndex());
                dlm1.addElement(s);      
                validate();
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list1.getSelectedIndex() >= 1) {
                    delButton.setEnabled(true);
                } else {
                    delButton.setEnabled(false);
                }
                if(list1.getModel().getSize()>1) ok.setEnabled(true);
                else ok.setEnabled(false);
            }
        });
         delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = list1.getSelectedValue();
                dlm1.remove(list1.getSelectedIndex());
                dlm.addElement(s);      
                validate();
            }
        });
         ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(dlm1.getSize()<=count){
                String resultSet = "";
                for(int i=1; i<list1.getModel().getSize(); i++) {
                    list1.setSelectedIndex(i);
                    resultSet = resultSet+list1.getSelectedValue()+"-";
                }
                resultSet = resultSet.substring(0, resultSet.length()-1);
                try {
                    dispose();
                    ResultTable start_frame = new ResultTable(filials+"&&&"+resultSet);
                } catch (IOException ex) {
                    Logger.getLogger(UserSet.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                else output.setText("Выбирите менее "+(count+1)+" пользователей");
            }
        });
        contents.add(addButton);
        contents.add(delButton);
        addButton.setLocation(10, 50);
        contents.add(new JScrollPane(list));
        contents.add(new JScrollPane(list1));
        contents.add(ok);
        contents.add(output);
        setContentPane(contents);
        // Вывод окна
        setBounds(500, 150, 430, 280);
        setVisible(true);
    }
}
