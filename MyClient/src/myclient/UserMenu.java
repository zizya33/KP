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

public class UserMenu extends JFrame {
    
    final JFrame frame = new JFrame("Пользователь");
    
    public UserMenu(int size_x, int size_y) throws IOException  {
		frame.setBounds(500, 150, size_x, size_y);
		frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#EEE8AA"));
		frame.add(panel);
                JLabel lable = new JLabel("МЕНЮ");
                lable.setSize(300,30);
		lable.setLocation(80, 30);
                lable.setForeground(Color.decode("#8B4513"));
                panel.add(lable);
                final JButton show = new JButton("Ввести данные");
		show.setSize(160,30);
		show.setLocation(60, 70);
                show.setBackground(Color.decode("#8B4513"));
                show.setForeground(Color.WHITE);
                panel.add(show);
                final JButton edict = new JButton("Относит. индексы");
		edict.setSize(160,30);
		edict.setLocation(60, 120);
                edict.setBackground(Color.decode("#8B4513"));
                edict.setForeground(Color.WHITE);
                panel.add(edict);
                final JButton grafic = new JButton("График");
		grafic.setSize(160,30);
		grafic.setLocation(60, 170);
                grafic.setBackground(Color.decode("#8B4513"));
                grafic.setForeground(Color.WHITE);
                panel.add(grafic);
               
                show.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ShowFrame start_frame = new ShowFrame();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                edict.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            UserFrame start_frame = new UserFrame();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                grafic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String data = MyClient.send("otnnn_ind");
                            BarChart_AWT chart = new BarChart_AWT("Относительные индексы", "Индексы качества", data);
                            chart.pack( );
                            chart.setVisible( true );
                        } catch (IOException ex) {
                            Logger.getLogger(UserMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
    }
    }