package myclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class DeleteFrame extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    
    public DeleteFrame(final String type) throws IOException
    {
        super("Администратор");
        // Создание панели
        JPanel contents = new JPanel();
        String data = MyClient.send(type);
        contents.setBackground(Color.decode("#8B4513"));
        dlm.add(0, "---------------------------------------------");
        String[] parts = data.split("-");
        for (String string : parts) {
            dlm.add(0, string);
        }
       
        // Создание кнопки
        final JButton removeButton = new JButton("Удалить");
        removeButton.setBackground(Color.decode("#EEE8AA"));
        removeButton.setForeground(Color.WHITE);
        removeButton.setSize(120,30);
        removeButton.setLocation(30, 60);
        
        final JList<String> list = new JList<String>(dlm);
        //list.setFixedCellWidth(60);
        list.setBackground(Color.decode("#EEE8AA"));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    removeButton.setEnabled(true);
                } else {
                    removeButton.setEnabled(false);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = list.getSelectedValue();
                try {
                    String request = MyClient.send("delete|type: "+type+"|name: "+s+"|");
                    if(request.equalsIgnoreCase("true")) dlm.remove(list.getSelectedIndex());
                } catch (IOException ex) {
                    Logger.getLogger(DeleteFrame.class.getName()).log(Level.SEVERE, null, ex);
                }            
                validate();
            }
        });
        contents.add(removeButton);
        contents.add(new JScrollPane(list));
        
        setContentPane(contents);
        // Вывод окна
        setBounds(500, 150, 300, 300);
        setVisible(true);
    }
}

