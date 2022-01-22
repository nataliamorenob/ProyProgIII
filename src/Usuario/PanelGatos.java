package Usuario;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import BaseDeDatos.BD;
import Datos.Gatos;
import Ventanas.VentanaInicioSesion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//
public class PanelGatos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelGatos(Gatos g) {
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
		
		JLabel lblNombre = new JLabel("Nombre: "+g.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad: "+g.getEdad());
		lblEdad.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblEdad);
		
		JLabel lblSexo = new JLabel("Sexo: "+g.getSexo());
		lblSexo.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblSexo);
		
		JLabel lblPeso = new JLabel("Peso: "+g.getPeso());
		lblPeso.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPeso);
		
		JLabel lblCaracteristica = new JLabel("Carac: "+g.getCaracteristicas());
		lblCaracteristica.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblCaracteristica);
		
		JLabel lblTiempoAdopcion = new JLabel("Tiempo: "+g.getTiempoEnAdopcion());
		lblTiempoAdopcion.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblTiempoAdopcion);
		
		JLabel lblLocalizacion = new JLabel("Localización: "+g.getLocalizacion());
		lblLocalizacion.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblLocalizacion);
		
		JLabel lblColores = new JLabel("Colores: "+g.getColores());
		lblColores.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblColores);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(200, 200));
		add(panelCentro, BorderLayout.CENTER);
		
		ImageIcon im = new ImageIcon(g.getRutaFoto());
		im.setDescription(g.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
		
		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <Integer> respuestas = new ArrayList<>();
				int pregunta1 = JOptionPane.showConfirmDialog(null, "¿Ha tenido o tiene algún otro gato o mascota?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta2 = JOptionPane.showConfirmDialog(null, "¿Se considera capaz de afrontar los gastos que conlleva tener un gato?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta3 = JOptionPane.showConfirmDialog(null, "¿Su trabajo le requiere viajar constantemente?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta4 = JOptionPane.showConfirmDialog(null, "¿Considera que su gato se podría adaptar a su vivienda?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
				int pregunta5 = JOptionPane.showConfirmDialog(null, "¿Considera que podría proporcionar a su gato un entorno seguro?", "Cuestionario previo a la adopción", JOptionPane.YES_NO_OPTION);
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
					JOptionPane.showMessageDialog(null, "¡Enhorabuena! Consideramos que usted es un candidato idoneo para la adopción. \n Recuerde que esta información se comprobará el día de la adopción", "Cuestionario previo a la adopción",JOptionPane.INFORMATION_MESSAGE);
					String pregunta6 = JOptionPane.showInputDialog(null, "Introduzca su nombre de usuario:",  "Cuestionario previo a la adopción", JOptionPane.QUESTION_MESSAGE);
					if(pregunta6.equals(VentanaInicioSesion.uCon)) {
						BD.gatoReservado(con, g.getNombre());
				        PrintWriter pw = null;
				        try {
							
				        	pw = new PrintWriter(new FileWriter("animalesReservados.txt", true));
							ArrayList<Gatos> alGatosReservados = BD.obtenerGatos(con);
							for(Gatos g: alGatosReservados) {
								
								if(g.isReservado() == true) {
									String nombre = g.getNombre();
									Integer edad = g.getEdad();
									String sexo = g.getSexo();
									Integer peso = g.getPeso();
									String caracteristicas = g.getCaracteristicas();
									Integer tiempoEnAdopcion = g.getTiempoEnAdopcion();
									String localizacion = g.getLocalizacion();
									String colores = g.getColores();
									String rutaFoto = g.getRutaFoto();
									pw.println(nombre + ","+ edad + ","+ sexo + ","+ peso + ","+ colores + ","+ caracteristicas + ","+ tiempoEnAdopcion + ","+ localizacion); 
									BD.gatosACero(con);
									
									BD.borrarGatos(con, g.getNombre());
									panelCentro.removeAll();
									BD.obtenerGatos(con);
								}
							}
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							//PRINCIPIO CAMBIO
						}finally { 
							if(pw!=null) {
								pw.flush();
								pw.close();
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto",  "Cuestionario previo a la adopción", JOptionPane.ERROR_MESSAGE);
						
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
