package myclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminEdition extends JFrame {
    
    final JFrame frame = new JFrame("Администратор");
        
    public AdminEdition(int size_x, int size_y, final String type) throws IOException  {
		frame.setBounds(500, 150, size_x, size_y);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#EEE8AA"));
		frame.add(panel);
                final JButton add = new JButton("Добавить");
		add.setSize(140,30);
		add.setLocation(70, 70);
                add.setBackground(Color.decode("#8B4513"));
                add.setForeground(Color.WHITE);
                panel.add(add);
                final JButton del = new JButton("Удалить");
		del.setSize(140,30);
		del.setLocation(70, 120);
                del.setBackground(Color.decode("#8B4513"));
                del.setForeground(Color.WHITE);
                panel.add(del);
                final JButton edit = new JButton("Редактировать");
		edit.setSize(140,30);
		edit.setLocation(70, 170);
                edit.setBackground(Color.decode("#8B4513"));
                edit.setForeground(Color.WHITE);
                if(type.equalsIgnoreCase("user")||type.equalsIgnoreCase("admin"))panel.add(edit);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(type.equalsIgnoreCase("admin")||type.equalsIgnoreCase("user")){
                                AdminOrUserAdd start_frame = new AdminOrUserAdd(300, 300, type);
                            }
                            else {
                                if(type.equalsIgnoreCase("worker")) {
                                    WorkerAdd start_frame = new WorkerAdd(300, 300, type);
                                }
                                else {
                                     ProductAdd start_frame = new ProductAdd(300, 300, type);
                                }
                            }
                            frame.dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminEdition.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                del.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            DeleteFrame start_frame = new DeleteFrame(type);
                        } catch (IOException ex) {
                            Logger.getLogger(AdminEdition.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frame.dispose();
                    }
                });
                edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            EditModelTest start_frame = new EditModelTest(type);
                        } catch (IOException ex) {
                            Logger.getLogger(AdminEdition.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frame.dispose();
                    }
                });
    }
}
