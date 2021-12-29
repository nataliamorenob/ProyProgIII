package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaReservas extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaReservas;
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
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("RESERVAS");
		ImageIcon im = new ImageIcon("FOTOS/logo.jpg");
		this.setIconImage(im.getImage());
		ventanaReservas = this;
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel reservalbl = new JLabel("Animales Reservados");
		reservalbl.setFont(new Font("Baskerville Old Face", Font.PLAIN, 11));
		panelNorte.add(reservalbl);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver a Menu");
		btnVolver.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMenu();
				ventanaReservas.setVisible(false);
			}
		});
		
		//Leer el fichero animalesReservados
		File fichero = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    TreeSet<Animales> tsAnimales = new TreeSet();
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
        modeloTablaAnimales = new DefaultTableModel();
        modeloTablaAnimales.setColumnIdentifiers(columnas);
		
		for(Animales a: tsAnimales) {
			String dataRow[] = {a.getNombre(), String.valueOf(a.getEdad()), a.getSexo(), String.valueOf(a.getPeso()), a.getCaracteristicas()};
			modeloTablaAnimales.addRow(dataRow);
		}
		tablaAnimales = new JTable(modeloTablaAnimales);
		JScrollPane scrollTabla = new JScrollPane(tablaAnimales);
		panelCentro.add(scrollTabla);
		
		setVisible(true);
	}

}
