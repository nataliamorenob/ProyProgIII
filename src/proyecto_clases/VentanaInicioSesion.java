package proyecto_clases;
import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;
import java.util.regex.Pattern; 
import java.util.regex.Matcher;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

//holaa
public class VentanaInicioSesion extends JFrame {
	
	

	private JPanel contentPane, panelCentro, panelSur;
	private JTextField textFieldUsuario;
	private JButton btnExit, btnIniciarSesion, btnRegistrarse;
	private JLabel lblContrasenia, lblUsuario;
	private JPasswordField passwordFieldContrasenia;
	private JFrame ventanaInicioSesion;
	
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
	//EXPRESION REGULAR NATALIA TENGO QUE MEJORARLO PORQUE ME DA MAL

	
	
	
	/*boolean correcto=Pattern.matches(RE, passwordFieldContrasenia);
	if(correcto) {
		JOptionPane.showMessageDialog(null,"El nombre de usuario es correcto");
		
	}else {
		JOptionPane.showMessageDialog(null,"El nombre de usuario NO es correcto");
	}
	return m.matches();
	}*/
	
	

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
		
		//String RE = "[a-z]{1,8} + \\d{1,}";
		//String codigo="hola1";
		/*String RE = "[a-zA-Z]{5,10}";
		Pattern patron = Pattern.compile(RE);

		Matcher m = patron.matcher(textFieldUsuario.getText());
		boolean correcto=Pattern.matches(RE, textFieldUsuario.getText());
		if(correcto) {
			JOptionPane.showMessageDialog(null,"El nombre de usuario es correcto");
			
		}else {
			JOptionPane.showMessageDialog(null,"El nombre de usuario NO es correcto");
		}*/
		
		
		
		
		JLabel lblContrasenia = new JLabel("Contrase�a:");
		panelCentro.add(lblContrasenia);
		
		passwordFieldContrasenia = new JPasswordField();
		passwordFieldContrasenia.setText("");
		passwordFieldContrasenia.setColumns(10);
		panelCentro.add(passwordFieldContrasenia);
		
		
	/*	private boolean comprobarUsuarioER(String passwordFieldContrasenia) {
			String RE = "[a-zA-Z]{5,10}";
			Pattern patron = Pattern.compile(RE);
			Matcher m=Pattern.matcher(passwordFieldContrasenia.getText()); 
		
			if(passwordFieldContrasenia.isEmpty() || passwordFieldContrasenia=="" ) {
				JOptionPane.showMessageDialog(null, "El campo no puede estar vacío");
				return false;
			}else if(!m.matches()) {
				JOptionPane.showMessageDialog(null, "Contraseña no valida");
				return false;
			}
			return true;
		}*/
	
		
		//BOTONES
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuario=textFieldUsuario.getText();
				String contrasenia=passwordFieldContrasenia.getText();
				
				if(!usuario.equals("") & !contrasenia.equals("")) {
					BD.initBD("BaseDatos.db"); 
					int result=BD.cogerUsuario(usuario, contrasenia);
					if(result==0) {
						JOptionPane.showMessageDialog(null, "Aun no te has registrado");
						btnRegistrarse.setEnabled(true);
					
					}else if(result==1) {
						JOptionPane.showMessageDialog(null, "La contrase�a es incorrecta");
						
					}else {
						JOptionPane.showMessageDialog(null, "Bienvenido");
						//Una vez se haya registrado correctamente accederemos a la VentanaMenu
						new VentanaMenu();
						ventanaInicioSesion.setVisible(false);
					}
					BD.closeBD();
				}
				textFieldUsuario.setText("");
				passwordFieldContrasenia.setText("");
			}
		});
		
		
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuario=textFieldUsuario.getText();
				String contrasenia=passwordFieldContrasenia.getText();
				if(!usuario.equals("") & !contrasenia.equals("")) {
					BD.initBD("BaseDatos.db");   
					int result=BD.cogerUsuario(usuario, contrasenia);
					if(result==0) {
						BD.anyadirUsuario(usuario, contrasenia);
						BD.closeBD();
						btnRegistrarse.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente.");
						
					}else {
						JOptionPane.showMessageDialog(null, "Este nombre de usuario ya esta en uso.");
					}
				}
				textFieldUsuario.setText("");
				passwordFieldContrasenia.setText("");
			}
		});
		
		
		setVisible(true);
	}
	/*private  comprobarUsuarioER(String textFieldUsuario) {
		String RE = "[a-zA-Z]{5,10}";
		Pattern patron = Pattern.compile(RE);

		Matcher m = patron.matcher(textFieldUsuario.getText());
		boolean correcto=Pattern.matches(RE, textFieldUsuario.getText());
		if(correcto) {
			JOptionPane.showMessageDialog(null,"El nombre de usuario es correcto");
			
		}else {
			JOptionPane.showMessageDialog(null,"El nombre de usuario NO es correcto");
		}
	}*/


	
	//private void activacionBotones() {
	//	btn
	//}



}
