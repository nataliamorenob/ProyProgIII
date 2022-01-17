package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Administrador.PanelAccesoriosAdmin;
import Administrador.PanelAlimentosAdmin;
import Administrador.PanelGatosAdmin;
import Administrador.PanelOtrosAdmin;
import Administrador.PanelPerrosAdmin;
import BaseDeDatos.BD;
import Datos.Accesorios;
import Datos.Alimentos;
import Datos.Gatos;
import Datos.Otros;
import Datos.Perros;


public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private Connection con;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		ImageIcon im = new ImageIcon("FOTOS/logo.jpg");
		this.setIconImage(im.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("PROTECTORA");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		con = BD.initBD("BaseDatos.db");
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		
		JPanel panelMenu = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPanelCentro = new JScrollPane(panelCentro);
		JPanel panelAbajo = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();

		JButton btnAnyadir = new JButton("Añadir");


		btnAnyadir.setFont(new Font("Bodoni MT", Font.PLAIN, 11));

		
		JMenu mnAnimales = new JMenu("Animales");
		mnAnimales.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenuItem mntmPerro = new JMenuItem("Perros");
		mntmPerro.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenuItem mntmGato = new JMenuItem("Gatos");
		mntmGato.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenuItem mntmOtro = new JMenuItem("Otros");
		mntmOtro.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenu mnAccesorios = new JMenu("Accesorios");
		mnAccesorios.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenu mnAlimentos = new JMenu("Alimentos");
		mnAlimentos.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		
		//AñADIR LOS COMPONENTES A LOS PANELES
		contentPane.add(panelMenu, BorderLayout.NORTH);
		contentPane.add(scrollPanelCentro, BorderLayout.CENTER);
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		panelMenu.add(menuBar);
		menuBar.add(mnAnimales);
		menuBar.add(mnAccesorios);
		menuBar.add(mnAlimentos);

		mnAnimales.add(mntmPerro);
		mnAnimales.add(mntmGato);
		mnAnimales.add(mntmOtro);
		
		panelAbajo.add(btnAnyadir);
		
		mntmPerro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> alPerros = BD.obtenerPerros(con);
				for(Perros p: alPerros) {
					panelCentro.add(new PanelPerrosAdmin(p));
				}
				BD.closeBD();
				panelCentro.updateUI();
				
			}
		});

		mntmGato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				con = BD.initBD("BaseDatos.db");
				ArrayList<Gatos> alGatos = BD.obtenerGatos(con);
				for(Gatos g: alGatos) {
					panelCentro.add(new PanelGatosAdmin(g));
				}
				BD.closeBD();
				panelCentro.updateUI();
			}
		});
		
		mntmOtro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll();
				con = BD.initBD("BaseDatos.db");
				ArrayList<Otros> alOtros = BD.obtenerOtros(con);
				for(Otros o: alOtros) {
					panelCentro.add(new PanelOtrosAdmin(o));
				}
				BD.closeBD();
				panelCentro.updateUI();
			}
		});
		
		mnAccesorios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				con=BD.initBD("BaseDatos.db");
				ArrayList<Accesorios> alAccesorios=BD.obtenerAccesorios(con);
				for(Accesorios acs: alAccesorios) {
					panelCentro.add(new PanelAccesoriosAdmin(acs));
				}
				BD.closeBD();
				panelCentro.updateUI();
			}
		});
		
		mnAlimentos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				con=BD.initBD("BaseDatos.db");
				ArrayList<Alimentos> alAlimentos=BD.obtenerAlimentos(con);
				for(Alimentos al: alAlimentos) {
					panelCentro.add(new PanelAlimentosAdmin(al));
				}
				BD.closeBD();
				panelCentro.updateUI();
			}
		});
		
		btnAnyadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object []  opciones= { "Perro", "Gato", "Otro", "Alimentos", "Accesorios"};
				Object opcion = JOptionPane.showInputDialog(null,"Seleccione lo que desee añadir:", "Elegir", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				if(opcion.equals("Perro")) {
					String nombre = JOptionPane.showInputDialog("Introduzca el nombre del perro que desea añadir: ");
					int edad = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la edad del perro: "));
					String sexo = JOptionPane.showInputDialog("Introduzca el sexo correspondiente: ");
					int peso = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la peso del perro: "));
					String carac = JOptionPane.showInputDialog("Determine las caracterisitcas del animal: ");
					int tiempoAdop = Integer.parseInt(JOptionPane.showInputDialog("Añada el tiempo que lleva en adopción dicho animal: "));
					String localizacion = JOptionPane.showInputDialog("Introduzca lo localización en la que se encuentre el animal: ");
					String colores = JOptionPane.showInputDialog("Determine los colores del perro: ");
					boolean reservado = Boolean.parseBoolean(JOptionPane.showInputDialog("Indique si el animal está reservado: "));
					String rutaFoto = JOptionPane.showInputDialog("Introduzca la ruta de la foto:");
					BD.anyadirPerro(con, nombre, edad, sexo, peso, carac, tiempoAdop, localizacion, colores, reservado, rutaFoto);
					panelCentro.removeAll();
					BD.obtenerPerros(con);
				}
				else if(opcion.equals("Gato")) {
					String nombre = JOptionPane.showInputDialog("Introduzca el nombre del gato que desea añadir: ");
					int edad = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la edad del gato: "));
					String sexo = JOptionPane.showInputDialog("Introduzca el sexo correspondiente: ");
					int peso = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la peso del gato: "));
					String carac = JOptionPane.showInputDialog("Determine las caracterisitcas del animal: ");
					int tiempoAdop = Integer.parseInt(JOptionPane.showInputDialog("Añada el tiempo que lleva en adopción dicho animal: "));
					String localizacion = JOptionPane.showInputDialog("Introduzca lo localización en la que se encuentre el animal: ");
					String colores = JOptionPane.showInputDialog("Determine los colores del gato: ");
					boolean reservado = Boolean.parseBoolean(JOptionPane.showInputDialog("Indique si el animal está reservado: "));
					String rutaFoto = JOptionPane.showInputDialog("Introduzca la ruta de la foto:");
					BD.anyadirGato(con, nombre, edad, sexo, peso, carac, tiempoAdop, localizacion, colores, reservado, rutaFoto);
					panelCentro.removeAll();
					BD.obtenerGatos(con);
				}
				else if(opcion.equals("Otro")) {
					String nombre = JOptionPane.showInputDialog("Introduzca el nombre del animal que desea añadir: ");
					int edad = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la edad del gato: "));
					String sexo = JOptionPane.showInputDialog("Introduzca el sexo correspondiente: ");
					int peso = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la peso del gato: "));
					String carac = JOptionPane.showInputDialog("Determine las caracterisitcas del animal: ");
					boolean peligtoExt = Boolean.parseBoolean(JOptionPane.showInputDialog("Indique si el animal está en el peligro de extinción: "));
					String rutaFoto = JOptionPane.showInputDialog("Introduzca la ruta de la foto:");
					BD.anyadriOtro(con, nombre, edad, sexo, peso, carac, peligtoExt, rutaFoto);
					panelCentro.removeAll();
					BD.obtenerOtros(con);
				}
				else if(opcion.equals("Alimentos")) {
					String nombre = JOptionPane.showInputDialog("Introduzca el nombre del alimento que vaya a añadir: ");
					int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el precio del producto: "));
					String animalDirigido = JOptionPane.showInputDialog("Introduzca el animal para el que sea dirigido: ");
					String rutaFoto = JOptionPane.showInputDialog("Introduzca la ruta de la foto:");
					boolean enCesta = Boolean.parseBoolean(JOptionPane.showInputDialog("Indique si el alimento está reservado: "));
					long fechaCad = Long.parseLong(JOptionPane.showInputDialog("Indique la fecha de caducidad del alimento: "));
					Date d = new Date(fechaCad);
					String fechaCaducidad = sdf.format(d);
					//String fechaCaducidad = JOptionPane.showInputDialog("Indique la fecha de caducidad del alimento: ");
					BD.anyadirAlimento(con, nombre, precio, animalDirigido, rutaFoto, enCesta, fechaCaducidad);
					panelCentro.removeAll();
					BD.obtenerAlimentos(con);
				}
				else {
					String nombre = JOptionPane.showInputDialog("Introduzca el nombre del accesorio a añadir: ");
					int precio = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el precio del producto: "));
					String animalDirigido = JOptionPane.showInputDialog("Introduzca el animal para el que sea dirigido: ");
					String rutaFoto = JOptionPane.showInputDialog("Introduzca la ruta de la foto:");
					boolean enCesta = Boolean.parseBoolean(JOptionPane.showInputDialog("Indique si el accesorio está reservado: "));
					BD.anyadirAccesorio(con, nombre, precio, animalDirigido, rutaFoto, enCesta);
					panelCentro.removeAll();
					BD.obtenerAccesorios(con);
				}
			}
		});
	
	}

}
