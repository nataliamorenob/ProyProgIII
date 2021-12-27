package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;

public class VentanaReservas extends JFrame {

	private JPanel contentPane;
	private JTable tablaAnimales;
	private DefaultTableModel modeloTablaAnimales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReservas frame = new VentanaReservas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaReservas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel reservalbl = new JLabel("Animales Reservados");
		panelNorte.add(reservalbl);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		//Leer el fichero animalesReservados
		File fichero = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    TreeSet<Animales> tsAnimales = new TreeSet<>();
        try {
        	fichero = new File ("animalesReservados.txt");
			fr = new FileReader (fichero);
			br = new BufferedReader(fr);
			String linea = br.readLine();
			while(linea!=null) {
				String [] datos = linea.split(",");
				String nombre = datos[0];
				Integer edad = Integer.parseInt(datos[1]);
				String sexo = datos[2];
				Integer peso = Integer.parseInt(datos[3]);
				String caracteristicas = datos[4];
				Animales an = new Animales(nombre, edad, sexo, peso, caracteristicas, caracteristicas);
				tsAnimales.add(an);
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String [] columnas = {"Nombre","Edad","Sexo", "Peso", "Caracteristicas"};  
		modeloTablaAnimales.setColumnIdentifiers(columnas);
		
		for(Animales a: tsAnimales) {
			String dataRow[] = {a.getNombre(), String.valueOf(a.getEdad()), a.getSexo(), String.valueOf(a.getPeso()), a.getCaracteristicas()};
			modeloTablaAnimales.addRow(dataRow);
		}
		tablaAnimales = new JTable(modeloTablaAnimales);
		JScrollPane scrollTabla = new JScrollPane(tablaAnimales);
		panelCentro.add(scrollTabla);
	}

}
