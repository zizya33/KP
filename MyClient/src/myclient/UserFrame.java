package myclient;

import java.awt.Color;
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

public class UserFrame extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    private DefaultListModel<String> dlm1 = new DefaultListModel<String>();
    private DefaultListModel<String> dlm2 = new DefaultListModel<String>();
    JPanel contents;
    
    public UserFrame() throws IOException
    {
        super("ПОЛЬЗОВАТЕЛЬ");
        // Создание панели
        contents = new JPanel();
        contents.setBackground(Color.decode("#8B4513"));
        String data = MyClient.send("otn_ind");
        String[] parts = data.split("--");
        String[][] newMatrix = new String[parts.length][];
        String res = "";
        for(int i=0; i<parts.length; i++){
            res+= parts[i]+"\r\n";            
            newMatrix[i] = parts[i].split("-");
            
        }
        
	
	FileWork fw=new FileWork ("file.txt", res);
            fw.work();
        
        
        dlm.add(0, "Название товара");
        dlm.add(1, "---------------");
        
        dlm1.add(0, "Индекс кач-ва");
        dlm1.add(1, "--------------");
        
        dlm2.add(0, "Характеристика");
        dlm2.add(1, "---------------");
        
        for(int i=0; i<parts.length; i++){
            dlm.add(i+2, newMatrix[i][0]);
            dlm2.add(i+2, newMatrix[i][1]);
            dlm1.add(i+2, newMatrix[i][2]);
        }
        

        final JList<String> list = new JList<String>(dlm);
        final JList<String> list1 = new JList<String>(dlm1);
        final JList<String> list2 = new JList<String>(dlm2);
        list.setPreferredSize( new Dimension(130, 180));
        list.setBackground(Color.decode("#EEE8AA"));
        list1.setPreferredSize( new Dimension(130, 180));
        list1.setBackground(Color.decode("#EEE8AA"));
        list2.setPreferredSize( new Dimension(130, 180));
        list2.setBackground(Color.decode("#EEE8AA"));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() >= 1) {
                    list1.setSelectedIndex(list.getSelectedIndex() );
                    list2.setSelectedIndex(list.getSelectedIndex() );
                } else {
                    list1.clearSelection();
                    list2.clearSelection();
                    //addButton.setEnabled(false);
                }
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list1.getSelectedIndex() >= 1) {
                    list.setSelectedIndex(list1.getSelectedIndex() );
                    list2.setSelectedIndex(list1.getSelectedIndex() );
                } else {
                    list.clearSelection();
                    list2.clearSelection();
                }
                
            }
        });
        
        list2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list2.getSelectedIndex() >= 1) {
                    list1.setSelectedIndex(list2.getSelectedIndex() );
                    list2.setSelectedIndex(list2.getSelectedIndex() );
                } else {
                    list1.clearSelection();
                    list2.clearSelection();
                }
                
            }
        });

        contents.add(list);
        contents.add(list2);
        contents.add(list1);
        //contents.add(ok);
        setContentPane(contents);
        // Вывод окна
        setBounds(500, 150, 500, 280);
        setVisible(true);
    }
}
