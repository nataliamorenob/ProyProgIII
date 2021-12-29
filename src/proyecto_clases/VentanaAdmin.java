package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private Connection con;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		setExtendedState(MAXIMIZED_BOTH);
		
		//
		//
		
		
		
		JPanel panelMenu = new JPanel();
		panelCentro.setLayout(new GridLayout(0, 2));
		JScrollPane scrollPanelCentro = new JScrollPane(panelCentro);
		JPanel panelAbajo = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();

		JButton btnAnyadir = new JButton("A�adir");


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
		
		
		//A�ADIR LOS COMPONENTES A LOS PANELES
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

		btnAnyadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}

}