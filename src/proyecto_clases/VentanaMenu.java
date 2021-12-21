package proyecto_clases;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Taskbar.State;
import java.awt.Toolkit;

public class VentanaMenu extends JFrame { 

	private JPanel contentPane, panelCentro;
	private Connection con;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
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
	public VentanaMenu() {

		con=BD.initBD("BaseDatos.db");
		BD.crearTablas(con);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("PROTECTORA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//CREACIÓN DE COMPONENTES
		JPanel panelMenu = new JPanel();
		panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPanelCentro = new JScrollPane(panelCentro);
		JPanel panelAbajo = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		JButton btnReserva = new JButton("Reservas");
		btnReserva.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		JButton btnCarrito = new JButton("Cesta");
		btnCarrito.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		
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
		
		JMenu mnLocalizacion = new JMenu("Localizaci\u00F3n");
		mnLocalizacion.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		JMenu mnQS = new JMenu("Quienes Somos");
		mnQS.setFont(new Font("Baskerville Old Face", Font.PLAIN, 12));
		
		//AÑADIR LOS COMPONENTES A LOS PANELES
		contentPane.add(panelMenu, BorderLayout.NORTH);
		//contentPane.add(panelCentro, BorderLayout.CENTER);
		contentPane.add(scrollPanelCentro, BorderLayout.CENTER);
		//panelCentro.setLayout(new GridLayout(0, 3, 0, 0));
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		panelMenu.add(menuBar);
		menuBar.add(mnAnimales);
		menuBar.add(mnAccesorios);
		menuBar.add(mnAlimentos);
		menuBar.add(mnLocalizacion);
		menuBar.add(mnQS);
		mnAnimales.add(mntmPerro);
		mnAnimales.add(mntmGato);
		mnAnimales.add(mntmOtro);
		
		panelAbajo.add(btnReserva);
		panelAbajo.add(btnCarrito);
		panelAbajo.add(btnComprar); 
		btnComprar.setVisible(false);

		 
		
		//EVENTOS
		mntmPerro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); //Para eliminar todos los componentes que haya en el panelCentro y no se solapen
				//panelCentro.add(new PanelPerros());
				//panelCentro.setLayout(new GridLayout(0, 2));
				con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> alPerros = BD.obtenerPerros(con);
//				System.out.println(alPerros.size());
				//for(Perros p: alPerros) {
//					System.out.println(p.getRutaFoto());
//					ImageIcon im = new ImageIcon(p.getRutaFoto());
//					im.setDescription(p.getRutaFoto());
//					JLabel lbl = new JLabel(im);
//					panelCentro.add(lbl);
				//	panelCentro.add(new PanelPerros(p));
				//}
				cargarPerros(alPerros,0);
				BD.closeBD();
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mntmGato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				con = BD.initBD("BaseDatos.db");
				ArrayList<Gatos> alGatos = BD.obtenerGatos(con);
				for(Gatos g: alGatos) {
					panelCentro.add(new PanelGatos(g));
				}
				BD.closeBD();
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mntmOtro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelOtros());
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mnLocalizacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelLocalizacion());
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mnQS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelQuienesSomos());
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mnAccesorios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelAccesorios());
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		mnAlimentos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelAlimentos());
				panelCentro.updateUI();
				btnComprar.setVisible(false);
			}
		});
		
		btnReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Desde aqui accederemos a la ventana de animales reservados
				
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Desde aquí accederemos a la ventana comprar
				
			}
		});
		
		btnCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Accedemos a la ventana de las compras que deseamos hacer
				
			}
		});
		
		setVisible(true);
	}
	
	/**
	 * Método recursivo para recorrer el ArrayList de Perros
	 * @param perros <- ArrayList que recorremos para cargar los Perros
	 * @param i <- Entero que utilizamos para recorrer el ArrayList
	 */
	private void cargarPerros(ArrayList<Perros> perros, int i) {
		if(i<perros.size()) {
			panelCentro.add(new PanelPerros(perros.get(i)));
			cargarPerros(perros, i+1);
		}
	}
}
