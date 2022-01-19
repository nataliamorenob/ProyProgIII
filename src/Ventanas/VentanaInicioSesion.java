package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class VentanaInicioSesion extends JFrame {
	
	

	private JPanel contentPane, panelCentro, panelSur;
	private JTextField textFieldUsuario;
	private JButton btnExit, btnIniciarSesion, btnRegistrarse;
	private JLabel lblContrasenia, lblUsuario;
	private JPasswordField passwordFieldContrasenia;
	private JFrame ventanaInicioSesion;
	private Connection con;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion frame = new VentanaInicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 * @return 
	 */
	public VentanaInicioSesion() {
		BD.initBD("BaseDatos.db");
		BD.closeBD();
		ImageIcon im = new ImageIcon("FOTOS/logo.jpg");
		this.setIconImage(im.getImage());
		ventanaInicioSesion = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("VENTANA INICIO SESION");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnExit = new JButton("Exit");
		panelSur.add(btnExit);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		panelSur.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Crear cuenta");
		panelSur.add(btnRegistrarse);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		panelCentro.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		panelCentro.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		

		
		
		
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		panelCentro.add(lblContrasenia);
		
		passwordFieldContrasenia = new JPasswordField();
		passwordFieldContrasenia.setText("");
		passwordFieldContrasenia.setColumns(10);
		panelCentro.add(passwordFieldContrasenia);
		
		

		
		
		
		//BOTONES
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection con=BD.initBD("BaseDatos.db"); 
				BD.perrosACero(con);
				BD.gatosACero(con);
				BD.alimentosACero(con);
				BD.accesoriosACero(con);
				String usuario=textFieldUsuario.getText();
				String contrasenia=passwordFieldContrasenia.getText();
				
				if(!usuario.equals("") & !contrasenia.equals("")) {
					//Connection con=BD.initBD("BaseDatos.db"); 
					int result=BD.cogerUsuario(con, usuario, contrasenia);
					if(result==0) {
						JOptionPane.showMessageDialog(null, "Aun no te has registrado");
						btnRegistrarse.setEnabled(true);
					
					}else if(result==1) {
						JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
						
					}else {
						JOptionPane.showMessageDialog(null, "Bienvenido");
						if(usuario.equals("admin") && contrasenia.equals("adminadmin1")) {
							VentanaAdmin frame = new VentanaAdmin();
							frame.setVisible(true);
						}else {
							//Una vez que haya iniciado sesion correctamente accederemos a la VentanaMenu
							new VentanaMenu();
							ventanaInicioSesion.setVisible(false);
					}
					
				}
				textFieldUsuario.setText("");
				passwordFieldContrasenia.setText("");
				
				try {
					PrintWriter writer=new PrintWriter("animalesReservados.txt");
					PrintWriter writer2=new PrintWriter("cesta.txt");
					writer.print("");
					writer2.print("");
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}

		});
		
		
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuario=textFieldUsuario.getText();
				String contrasenia=passwordFieldContrasenia.getText();
				String patCntER = "[A-Za-z]{8,}[0-9]";
				
				if(!usuario.equals("") && !contrasenia.equals("") && passwordFieldContrasenia.getText().matches(patCntER)) {
					Connection con=BD.initBD("BaseDatos.db");   
					int result=BD.cogerUsuario(con, usuario, contrasenia);
					if(result==0) {
						BD.anyadirUsuario(con, usuario, contrasenia);
						BD.closeBD();
						btnRegistrarse.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente.");
						
					}else {
						JOptionPane.showMessageDialog(null, "Este nombre de usuario ya esta en uso.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos, 8 carácteres y un dígito");
				}
				
				textFieldUsuario.setText("");
				passwordFieldContrasenia.setText("");
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		
		/**
		 * Hilo que cambia de color lblUsuario y lblContrasenia
		 */
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while(true) {
					lblUsuario.setForeground(Color.BLACK);
					lblContrasenia.setForeground(Color.ORANGE);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lblUsuario.setForeground(Color.ORANGE);
					lblContrasenia.setForeground(Color.BLACK);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		
		
		setVisible(true);
	}
	

	
	
	

	






}
