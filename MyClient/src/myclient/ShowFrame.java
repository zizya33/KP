package myclient;

import java.awt.Color;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowFrame  extends JFrame{
    // Модель списка
    private DefaultListModel<String> dlm = new DefaultListModel<String>();
    JTable table = new JTable();
    JPanel contents;
    final JButton edit;
    final JButton delete; 
    private EditDialog dlg;
    public ShowFrame() throws IOException
    {
        super("Пользователь");
        // Создание панели
        dlg = new EditDialog(this, true);
        dlg.setBounds(500, 150, 300, 300);
        
        dlg.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
        contents = new JPanel();
        
        contents.setBackground(Color.decode("#8B4513"));
        
        String data = MyClient.send("prod|");
        
        InitTable (data);
        
        
        edit = new JButton("Изменить");
		edit.setSize(100,30);
		edit.setLocation(40, 70);
                edit.setBackground(Color.decode("#EEE8AA"));
                edit.setForeground(Color.black);
                contents.add(edit);
                
                delete = new JButton("Удалить");
		delete.setSize(100,30);
		delete.setLocation(150, 70);
                delete.setBackground(Color.decode("#EEE8AA"));
                delete.setForeground(Color.black);
                //contents.add(delete);
                
                edit.setEnabled(false);
                delete.setEnabled(false);
                
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });  
       
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButActionPerformed(evt);
            }
        });

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButActionPerformed(evt);
            }
        });
        table.setBackground(Color.decode("#EEE8AA"));        
        contents.add(table);
        setContentPane(contents);
        setBounds(500, 150, 400, 200);
        setVisible(true);
    }
    
     private void TableMouseClicked(java.awt.event.MouseEvent evt) {                                       
            if(table.getSelectedRowCount() > 0)
            {
                edit.setEnabled(true);
                delete.setEnabled(true);
            }
            else
            {
                edit.setEnabled(false);
                delete.setEnabled(false);
            }
       }
     private void EditButActionPerformed(java.awt.event.ActionEvent evt){
        dlg.setTh_val((String)table.getValueAt(table.getSelectedRow(), 2)); 
        dlg.setPrice((String)table.getValueAt(table.getSelectedRow(), 3)); 
        dlg.setQuant((String)table.getValueAt(table.getSelectedRow(), 4));
        dlg.setVisible(true);
        
        if (dlg.flag){
        try
{
String insertStr = "update|this_value-"
+ dlg.getTh_val() + "-name-" + (String)table.getValueAt(table.getSelectedRow(), 0)+"|";
String data=MyClient.send(insertStr);
if (data.equals("ok")){
//int done = stmt.executeUpdate(insertStr);
insertStr = "update|price-"
+ dlg.getPrice() + "-name-" + (String)table.getValueAt(table.getSelectedRow(), 0)+"|";
String data1=MyClient.send(insertStr);
if (data1.equals("ok")){
//done = stmt.executeUpdate(insertStr);
insertStr = "update|quantity-"
+ dlg.getQuant() + "-name-" + (String)table.getValueAt(table.getSelectedRow(), 0)+"|";
String data2=MyClient.send(insertStr);
//done = stmt.executeUpdate(insertStr);

//String data1 = MyClient.send(insertStr);
if (data2.equals("ok")){
    //contents.setVisible(false);
    //getContentPane().removeAll();
    contents.removeAll();
contents=new JPanel();
contents.add(edit);
contents.add(delete);
String data3 = MyClient.send("prod|");
InitTable(data3);
contents.add(table);
setContentPane(contents);
//contents.setVisible(true);
}}}
//}
//else System.out.println("Error occured in editing data");
}
catch(Exception e)
{
System.out.println("Error occured in editing data");
//label1.setText("Error occured in editing data");
}}
        
     }
     private void DeleteButActionPerformed(java.awt.event.ActionEvent evt){
         
     }
     
     
     private void InitTable(String data){
        String [] colName = {"Название", "Характерист.","Значение","Цена","Кол-во"};
        String[] rows = data.split("--");
        String[][] newMatrix = new String[rows.length][];
        for(int i=0; i<rows.length; i++){
            newMatrix[i] = rows[i].split("-");
        }
        
        final DefaultTableModel model = new DefaultTableModel(rows.length+1, newMatrix[0].length);
        table = new JTable(model);
        
        for (int i=0;i<5;i++){
            model.setValueAt(colName[i], 0, i);
        }
        
        for (int i=0;i<newMatrix.length;i++){
            for (int j=0;j<newMatrix[0].length;j++){
                 model.setValueAt(newMatrix[i][j], i+1, j);     
            }
        }
     }
}

