package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAlimentosAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAlimentosAdmin(Alimentos a) {
		Connection con;
		con=BD.initBD("BaseDatos.db");
		setLayout(new BorderLayout(0, 0));
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		

		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(200, 200));
		add(panelCentro, BorderLayout.CENTER);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.borrarAlimentos(con, a.getNombre());
				panelCentro.removeAll();
				BD.obtenerPerros(con);
			}
		});
		panelSur.add(btnEliminar);
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(5, 0, 0, 0));
		panelDerecha.setPreferredSize(new Dimension(200,200));
		
		JLabel lblNombre = new JLabel("Nombre: "+a.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio: "+a.getPrecio()+"€");
		lblPrecio.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPrecio);
		
		JLabel lblAnimalDirigido = new JLabel("Animal dirigido: "+a.getAnimal_dirigido());
		lblAnimalDirigido.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblAnimalDirigido);
	
		
		ImageIcon im = new ImageIcon(a.getRutaFoto());
		im.setDescription(a.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
	}

}
