package Ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Datos.Animales;

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
	private Iterator iterador;

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
	@SuppressWarnings("serial")
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
		
		JLabel lblReserva = new JLabel("Animales Reservados");
		lblReserva.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
		panelNorte.add(lblReserva);
		
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
	    ArrayList<Animales> alAnimales = new ArrayList();
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
				String caracteristicas = datos[5];
				Animales an = new Animales(nombre, edad, sexo, peso, caracteristicas);
				alAnimales.add(an);
				linea = br.readLine();
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
        
        String [] columnas = {"Nombre","Edad","Sexo", "Peso", "Caracteristicas"};  
        modeloTablaAnimales = new DefaultTableModel() {
        	//Para que la tabla no pueda ser editada
        	public boolean isCellEditable(int row, int column) {
    			return false;
    		}
        };
        
        modeloTablaAnimales.setColumnIdentifiers(columnas);
        
        for(Animales a: alAnimales) {
        	String dataRow[] = {a.getNombre(), String.valueOf(a.getEdad()), a.getSexo(), String.valueOf(a.getPeso()), a.getCaracteristicas()};
			modeloTablaAnimales.addRow(dataRow);
        }
        
		tablaAnimales = new JTable(modeloTablaAnimales);
		JScrollPane scrollTabla = new JScrollPane(tablaAnimales);
		panelCentro.add(scrollTabla);
		
		JTableHeader th;
		th = tablaAnimales.getTableHeader();
		Font fuente = new Font("Bell MT", Font.PLAIN, 12);
		th.setFont(fuente);
		th.setBackground(Color.WHITE);
		
		tablaAnimales.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablaAnimales.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaAnimales.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaAnimales.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablaAnimales.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		
		//Alinear las columnas poniendolas en el centro de las celdas
		tablaAnimales.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				DefaultTableCellRenderer modeloCnt = new DefaultTableCellRenderer();
				modeloCnt.setHorizontalAlignment(CENTER);
				tablaAnimales.getColumnModel().getColumn(0).setCellRenderer(modeloCnt);
				tablaAnimales.getColumnModel().getColumn(1).setCellRenderer(modeloCnt);
				tablaAnimales.getColumnModel().getColumn(2).setCellRenderer(modeloCnt);
				tablaAnimales.getColumnModel().getColumn(3).setCellRenderer(modeloCnt);
				tablaAnimales.getColumnModel().getColumn(4).setCellRenderer(modeloCnt);
				return c;
			}
		});
		
		
		setVisible(true);
	}

}
