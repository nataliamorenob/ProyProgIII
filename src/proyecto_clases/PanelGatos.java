package proyecto_clases;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class PanelGatos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelGatos(Gatos g) {
		setLayout(new BorderLayout(0, 0));
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <Integer> respuestas = new ArrayList<>();
				int pregunta1 = JOptionPane.showConfirmDialog(null, "�Ha tenido o tiene alg�n otro gato o mascota?", "Cuestionario previo a la adopci�n", JOptionPane.YES_NO_OPTION);
				int pregunta2 = JOptionPane.showConfirmDialog(null, "�Se considera capaz de afrontar los gastos que conlleva tener un gato?", "Cuestionario previo a la adopci�n", JOptionPane.YES_NO_OPTION);
				int pregunta3 = JOptionPane.showConfirmDialog(null, "�Su trabajo le requiere viajar constantemente?", "Cuestionario previo a la adopci�n", JOptionPane.YES_NO_OPTION);
				int pregunta4 = JOptionPane.showConfirmDialog(null, "�Considera que su gato se podr�a adaptar a su vivienda?", "Cuestionario previo a la adopci�n", JOptionPane.YES_NO_OPTION);
				int pregunta5 = JOptionPane.showConfirmDialog(null, "�Considera que podr�a proporcionar a su gato un entorno seguro?", "Cuestionario previo a la adopci�n", JOptionPane.YES_NO_OPTION);
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
					JOptionPane.showConfirmDialog(null, "�Enhorabuena! Consideramos que usted es un candidato id�neo para la adopci�n. \n Recuerde que est� informaci�n se comprobar� el d�a de la adoci�n", "Cuestionario previo a la adopci�n",JOptionPane.OK_OPTION);
						
				}
				
				else {
					JOptionPane.showConfirmDialog(null,  "Lo lamentamos pero no consideramos que sea apto para la adopci�n. \n Para m�s informaci�n contacte con nosotros. \n Gracias.", "Cuestionario previo a la adopci�n", JOptionPane.OK_OPTION);
				}
				
					
				
				
			}
		});
		panelSur.add(btnReservar);
		
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
		
		JLabel lblLocalizacion = new JLabel("Localizaci�n: "+g.getLocalizacion());
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


	}
}
