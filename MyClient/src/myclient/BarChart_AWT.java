/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclient;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame
{
   public BarChart_AWT( String applicationTitle , String chartTitle, String data)
   {
       
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(data),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset(String data)
   {
       String[] parts = data.split("--");
        String[][] newMatrix = new String[parts.length][];
        for(int i=0; i<parts.length; i++){
            newMatrix[i] = parts[i].split("-");
        }
      
        
      final String [] names = new String [parts.length];
      for (int i=0; i<parts.length; i++){
            names[i] = newMatrix[i][0];
        }
         
      final String index = "Индекс";        
      //final String millage = "Millage";        
      //final String userrating = "User Rating";        
      //final String safety = "safety";        
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      for (int i=0; i<parts.length; i++)
      dataset.addValue( Float.parseFloat(newMatrix[i][2]) , index , names[i] );        
      

      return dataset; 
   }
//   public static void main( String[ ] args )
//   {
//      BarChart_AWT chart = new BarChart_AWT("Car Usage Statistics", "Which car do you like?", "123");
//      chart.pack( );        
//      RefineryUtilities.centerFrameOnScreen( chart );        
//      chart.setVisible( true ); 
//   }
}