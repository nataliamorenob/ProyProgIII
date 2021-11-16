import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane, panelCentro, panelSur;
	private JTextField textFieldUsuario;
	private JButton btnExit, btnInicioSesion, btnRegistro;
	private JLabel lblContrasenia, lblUsuario;
	private JPasswordField passwordFieldContrasenia;

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
	 */
	public VentanaInicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("VENTANA INICIO SESIÓN");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnExit = new JButton("Exit");
		panelSur.add(btnExit);
		
		JButton btnInicioSesion = new JButton("Iniciar sesión");
		panelSur.add(btnInicioSesion);
		
		JButton btnRegistro = new JButton("Crear cuenta");
		panelSur.add(btnRegistro);
		
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
		
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usuario=textFieldUsuario.getText();
				String contrasenia=passwordFieldContrasenia.getText();
				
				if(!usuario.equals("") & !contrasenia.equals("")) {
					//Connection con=BaseDeDatos.initBD("");   //TENGO QUE CREAR LA BASE Y PONER EL NOMBRE
					//int resul=BaseDeDatos.
					
				}
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setVisible(true);
	}



}
