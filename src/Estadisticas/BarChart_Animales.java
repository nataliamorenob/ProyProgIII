package Estadisticas;


import java.sql.Connection;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import BaseDeDatos.BD;

public class BarChart_Animales extends ApplicationFrame {
	private Statistics statistics;
	
	public BarChart_Animales( String applicationTitle , String chartTitle ) {
	      super( applicationTitle );        
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Category",            
	         "Score",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel ); 
	   }
	   
	   private CategoryDataset createDataset( ) {
		  statistics = new Statistics();
	      ArrayList<Integer>SexPerro = statistics.getPerrosSex();
	      ArrayList<Integer>SexGato = statistics.getGatosSex();
	      ArrayList<Integer>SexOtro = statistics.getOtrosSex();
	
	    		  
		  final String perro = "Perros"; 
		  final String gato = "Gatos";  
		  final String otro = "Otros";  
		  
	           
	      final String hembra = "H";        
	      final String macho = "M";        
	            
	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( );  
	      //Datos Perro
	      dataset.addValue( SexPerro.get(0) , perro , macho );        
	      dataset.addValue( SexPerro.get(1), perro , hembra ); 
	      
	      //Datos Gato
	      dataset.addValue( SexGato.get(0) , gato , macho );        
	      dataset.addValue( SexGato.get(1), gato , hembra ); 
	      
	      //Datos otros
	      dataset.addValue( SexOtro.get(0) , otro , macho );        
	      dataset.addValue( SexOtro.get(1), otro , hembra ); 
	                  

	      return dataset; 
	   }
	   
	   public static void main( String[ ] args ) {
	      BarChart_Animales chart = new BarChart_Animales("Clasificacion de animales por sexo", 
	         "Animales");
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 
	   }

}
