package myclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultTable extends JFrame{
    
     JLabel output = new JLabel(" ");
    
    public void set(String tmp) {
            this.output.setText(tmp);
        }
    
    public ResultTable(final String data) throws IOException
    {
        super("ПОЛЬЗОВАТЕЛЬ");
        String[] parts = data.split("&&&",2);
        final String[] col = parts[0].split("-");
        final String[] row = parts[1].split("-");
        final String[][] newMatrix = new String[col.length][row.length];
        // Создание панели
        JPanel contents = new JPanel();
        // Модель таблицы
        final DefaultTableModel model = new DefaultTableModel(row.length+1, col.length+1);
        final JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        for (int i=1;i<row.length+1;i++) {
            model.setValueAt(row[i-1], i, 0);
        }
        for (int i=1;i<col.length+1;i++) {
            table.getColumnModel().getColumn(i-1).setPreferredWidth(150);
            model.setValueAt(col[i-1], 0, i);
        }
        table.getColumnModel().getColumn(col.length).setPreferredWidth(150);
        final JButton addButton = new JButton("Рассчитать");
        final JButton button = new JButton("Зарегистировать");
        button.setVisible(false);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                for (int i=1;i<row.length+1;i++){
                   for (int j=1;j<col.length+1;j++){
                    try{
                        if(model.getValueAt(i, j) != null){
                        try{
                            int tmp; String tmpS;
                            Object o=model.getValueAt(i, j);
                            if (o instanceof String) {
                                tmpS = o.toString();
                                newMatrix[i-1][j-1] = tmpS;
                                tmp = Integer.parseInt(tmpS);
                            } 
                        }
                        catch(Exception er) {
                            model.setValueAt(null, i, j);
                            isValid = false;
                            break;
                        }                        
                    }
                    else {
                        isValid = false;
                        break;
                    }
                    }catch(Exception er) {
                            model.setValueAt(null, i, j);
                            isValid = false;
                            break;
                        }
                }
                }
                if(isValid){
                    String array[][] = new String [row.length][col.length];
                    for (int i=1;i<row.length+1;i++){
                    for (int j=1;j<col.length+1;j++){
                        if(model.getValueAt(i, j) != null){
                        try{
                            Object o=model.getValueAt(i, j);
                            if (o instanceof String) {
                                array[i-1][j-1] = o.toString();
                            } 
                        }
                        catch(Exception er) {
                                model.setValueAt(null, i, j);
                                isValid = false;
                                break;
                            }                        
                        }
                    }
                }
                    String result = "";
                   for (int i=1;i<row.length+1;i++){
                    for (int j=1;j<col.length+1;j++){
                        result = result + array[i-1][j-1]+"-";
                    }
                    result = result + "-";
                   }
                   result = "matrix|" + result.substring(0, result.length()-2);
                   String answer = "";
                    try {
                        answer  = MyClient.send(result+"|");
                        button.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(ResultTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String[] values = answer.split("-");
                    for (int i=1;i<row.length+1;i++)  {
                        for (int j=1;j<col.length+1;j++)  {
                            model.setValueAt(null, i, j);
                        }
                         model.setValueAt((Object)"1", i, Integer.parseInt(values[i-1])+1);
                    }    
                    set("Добавлено");
                    
                }
                else set("Ошибка данных");
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                String res = "reg|";
                for (int i=1;i<row.length+1;i++){
                   for (int j=1;j<col.length+1;j++){
                        if(model.getValueAt(i, j) != null){
                        try{
                            int tmp; String tmpS;
                            Object o=model.getValueAt(0, j);
                            if (o instanceof String) {
                                tmpS = o.toString();
                                res = res + tmpS + "-";
                            } 
                            Object ob=model.getValueAt(i, 0);
                            if (o instanceof String) {
                                tmpS = ob.toString();
                                res = res + tmpS + "%";
                            } 
                                res = res + newMatrix[i-1][j-1] + "--";
                        }
                        catch(Exception er) {
                            model.setValueAt(null, i, j);
                        }                        
                    }
                }
                }
                res = res.substring(0, res.length()-2);
                    try {
                        MyClient.send(res+"|");
                    } catch (IOException ex) {
                        Logger.getLogger(ResultTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    set("Добавлено");
            }
        });
        contents.add(scrollPane);
        setContentPane(contents);
        contents.add(addButton);
        contents.add(button);
        contents.add(output);
        // Вывод окна
        setBounds(250, 50, 800, 600);
        setVisible(true);
    }
}
