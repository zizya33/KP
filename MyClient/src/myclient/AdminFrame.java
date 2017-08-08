package myclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminFrame extends JFrame {
    
    final JFrame frame = new JFrame("Администратор");
    
    public AdminFrame(int size_x, int size_y) throws IOException  {
		frame.setBounds(500, 150, size_x, size_y);
		frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#EEE8AA"));
		frame.add(panel);
                JLabel lable = new JLabel("РЕДАКТИРОВАНИЕ");
                lable.setSize(300,30);
		lable.setLocation(80, 20);
                lable.setForeground(Color.decode("#8B4513"));
                panel.add(lable);
                final JButton adminEdict = new JButton("Администраторы");
		adminEdict.setSize(140,30);
		adminEdict.setLocation(70, 70);
                adminEdict.setBackground(Color.decode("#8B4513"));
                adminEdict.setForeground(Color.WHITE);
                panel.add(adminEdict);
                final JButton userEdict = new JButton("Пользователи");
		userEdict.setSize(140,30);
		userEdict.setLocation(70, 120);
                userEdict.setBackground(Color.decode("#8B4513"));
                userEdict.setForeground(Color.WHITE);
                panel.add(userEdict);
                final JButton workerEdict = new JButton("Работники");
		workerEdict.setSize(140,30);
		workerEdict.setLocation(70, 170);
                workerEdict.setBackground(Color.decode("#8B4513"));
                workerEdict.setForeground(Color.WHITE);
                panel.add(workerEdict);
                
                
                final JButton productEdict = new JButton("Товары");
		productEdict.setSize(140,30);
		productEdict.setLocation(70, 220);
                productEdict.setBackground(Color.decode("#8B4513"));
                productEdict.setForeground(Color.WHITE);
                panel.add(productEdict);
                adminEdict.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AdminEdition start_frame = new AdminEdition(300,300, "admin");
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                userEdict.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AdminEdition start_frame = new AdminEdition(300,300, "user");
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                workerEdict.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AdminEdition start_frame = new AdminEdition(300,300, "worker");
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                productEdict.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AdminEdition start_frame = new AdminEdition(300,300, "product");
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}
