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
		setTitle("PROTECTORA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//CREACI�N DE COMPONENTES
		JPanel panelMenu = new JPanel();
		panelCentro = new JPanel();
		JPanel panelAbajo = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		JButton btnReserva = new JButton("Reservas");
		btnReserva.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		JButton btnCarrito = new JButton("Cesta");
		btnCarrito.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		
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
		
		//A�ADIR LOS COMPONENTES A LOS PANELES
		contentPane.add(panelMenu, BorderLayout.NORTH);
		contentPane.add(panelCentro, BorderLayout.CENTER);
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
		panelAbajo.add(btnReservar);
		panelAbajo.add(btnComprar);
		btnReserva.setVisible(false);  
		btnComprar.setVisible(false); 


		btnReservar.setVisible(false);
		btnComprar.setVisible(false);

		 
		
		//EVENTOS
		mntmPerro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); //Para eliminar todos los componentes que haya en el panelCentro y no se solapen
				//panelCentro.add(new PanelPerros());
				panelCentro.setLayout(new GridLayout(0, 3));
				con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> al = BD.obtenerPerros(con);
				System.out.println(al.size());
				for(Perros p: al) {
//					System.out.println(p.getRutaFoto());
//					ImageIcon im = new ImageIcon(p.getRutaFoto());
//					im.setDescription(p.getRutaFoto());
//					JLabel lbl = new JLabel(im);
//					panelCentro.add(lbl);
					panelCentro.add(new PanelPerros(p));
				}
				BD.closeBD();
				panelCentro.updateUI();
				btnReservar.setVisible(true);
				btnComprar.setVisible(false);
			}
		});
		
		mntmGato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelGatos());
				panelCentro.updateUI();
				btnReservar.setVisible(true);
				btnComprar.setVisible(false);
			}
		});
		
		mntmOtro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelOtros());
				panelCentro.updateUI();
				btnReservar.setVisible(false);
				btnComprar.setVisible(false);
			}
		});
		
		mnLocalizacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelLocalizacion());
				panelCentro.updateUI();
				btnReservar.setVisible(false);
				btnComprar.setVisible(false);
			}
		});
		
		mnQS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelQuienesSomos());
				panelCentro.updateUI();
				btnReservar.setVisible(false);
				btnComprar.setVisible(false);
			}
		});
		
		mnAccesorios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelAccesorios());
				panelCentro.updateUI();
				btnReservar.setVisible(false);
				btnComprar.setVisible(false);
			}
		});
		
		mnAlimentos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelCentro.removeAll(); 
				panelCentro.add(new PanelAlimentos());
				panelCentro.updateUI();
				btnReservar.setVisible(false);
				btnComprar.setVisible(false);
			}
		});
		
		btnReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Desde aqui accederemos a la ventana de animales reservados
				
			}
		});
		
		btnReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Reservamos los animales
				
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Desde aqu� accederemos a la ventana comprar
				
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
}
