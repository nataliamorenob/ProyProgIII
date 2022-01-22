package Usuario;
import javax.swing.JPanel;
import javax.swing.border.Border;

import BaseDeDatos.BD;
import Datos.Perros;
import Ventanas.VentanaInicioSesion;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;

import java.util.*;


public class PanelPerros extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public PanelPerros(Perros p) {

		Connection con;
		con=BD.initBD("BaseDatos.db");
		setLayout(new BorderLayout(0, 0));

		setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(8, 0, 0, 0));
		panelDerecha.setPreferredSize(new Dimension(200, 200));
		
		JLabel lblNombre = new JLabel("Nombre: "+p.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad: "+p.getEdad());
		lblEdad.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblEdad);
		
		JLabel lblSexo = new JLabel("Sexo: "+p.getSexo());
		lblSexo.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblSexo);
		
		JLabel lblPeso = new JLabel("Peso: "+p.getPeso());
		lblPeso.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPeso);
		
		JLabel lblCaracteristica = new JLabel("Carac: "+p.getCaracteristicas());
		lblCaracteristica.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblCaracteristica);
		
		JLabel lblTiempoAdopcion = new JLabel("Tiempo: "+p.getTiempoEnAdopcion());
		lblTiempoAdopcion.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblTiempoAdopcion);
		

		JLabel lblLocalizacion = new JLabel("Localizacion: "+p.getLocalizacion());
		lblLocalizacion.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblLocalizacion);
		
		JLabel lblColores = new JLabel("Colores: "+p.getColores());
		lblColores.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblColores);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(200, 200));
		add(panelCentro, BorderLayout.CENTER);
		
		ImageIcon im = new ImageIcon(p.getRutaFoto());
		im.setDescription(p.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
		
		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <Integer> respuestas = new ArrayList<>();

				int pregunta1 = JOptionPane.showConfirmDialog(null, "¿Ha tenido o tiene algun otro perro o mascota?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta2 = JOptionPane.showConfirmDialog(null, "¿Se considera capaz de afrontar los gastos que conlleva tener un perro?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta3 = JOptionPane.showConfirmDialog(null, "¿Su trabajo le requiere viajar constantemente?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta4 = JOptionPane.showConfirmDialog(null, "¿Considera que su perro se podria adaptar a su vivienda?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta5 = JOptionPane.showConfirmDialog(null, "¿Considera que el perro podria adaptarse a su entorno?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				//String pregunta6 = JOptionPane.showInputDialog(null, "Introduzca su nombre de usuario:",  "Cuestionario previo a la adopción", JOptionPane.QUESTION_MESSAGE);

				respuestas.add(pregunta1);
				respuestas.add(pregunta2);
				respuestas.add(pregunta3);
				respuestas.add(pregunta4);
				respuestas.add(pregunta5);
				
				//YES = 0 and NO = 1
				int contador = 0;
				for(Integer i : respuestas) {
					if(respuestas.get(i) == 0) {
						contador ++;
					}
				}
				if(contador >= 3) {
					JOptionPane.showMessageDialog(null, "¡Enhorabuena! Consideramos que usted es un candidato idoneo para la adopcion. \n Recuerde que esta información se comprobará el día de la adoción", "Cuestionario previo a la adopción", JOptionPane.INFORMATION_MESSAGE);
					String pregunta6 = JOptionPane.showInputDialog(null, "Introduzca contraseña:",  "Cuestionario previo a la adopción", JOptionPane.QUESTION_MESSAGE);
					
					if(pregunta6.equals(VentanaInicioSesion.uCon)) {
						
						//cogemos en iniciosesion el usuario
						//llamariamos a metodo en BD perro poner el usuario
						BD.perroReservado(con, p.getNombre());
						PrintWriter pw = null;
						try {
							//fw = new FileWriter("animalesReservados.txt");
							pw = new PrintWriter(new FileWriter("animalesReservados.txt", true)); 
							ArrayList<Perros> alPerrosReservados = BD.obtenerPerros(con);
							for(Perros p: alPerrosReservados) {
								
								if(p.isReservado() == true) {
									String nombre = p.getNombre();
									Integer edad = p.getEdad();
									String sexo = p.getSexo();
									Integer peso = p.getPeso();
									String caracteristicas = p.getCaracteristicas();
									Integer tiempoEnAdopcion = p.getTiempoEnAdopcion();
									String localizacion = p.getLocalizacion();
									String colores = p.getColores();
									String rutaFoto = p.getRutaFoto();
									pw.println(nombre + ","+ edad + ","+ sexo + ","+ peso + ","+ colores + ","+ caracteristicas + ","+ tiempoEnAdopcion + ","+ localizacion); 		
									BD.anyadirReserva(con, VentanaInicioSesion.usu, nombre);
									BD.perrosACero(con);
									
									BD.borrarPerros(con, p.getNombre());
									panelCentro.removeAll();
									BD.obtenerPerros(con);
								}
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							if(pw!=null) {
								pw.flush();
								pw.close();
							}
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña es incorrecta",  "Cuestionario previo a la adopción", JOptionPane.ERROR_MESSAGE);
						//new VentanaInicioSesion();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,  "Lo lamentamos pero no consideramos que sea apto para la adopción. \n Para más información contacte con nosotros. \n Gracias.", "Cuestionario previo a la adopción", JOptionPane.INFORMATION_MESSAGE);

				}
						
						
					}

			
				
			
			
		});
		
		panelSur.add(btnReservar);
		

		}


}



