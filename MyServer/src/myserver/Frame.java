package myserver;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame {
    public Frame(int size_x, int size_y) throws IOException  {
		JFrame jfrm = new JFrame("Сервер"); 
		jfrm.setBounds(550, 200, size_x, size_y);
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		jfrm.add(panel);
                panel.setBackground(Color.decode("#EEE8AA"));
		JLabel jlab = new JLabel("СЕРВЕР ЗАПУЩЕН");
                jlab.setSize(300,30);
		jlab.setLocation(81, 40);
                jlab.setForeground(Color.BLACK);
                panel.add(jlab);
	}
}
