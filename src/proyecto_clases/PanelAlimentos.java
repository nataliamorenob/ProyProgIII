package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelAlimentos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAlimentos(Alimentos al) {
		Connection con;
		con=BD.initBD("BaseDatos.db");
		
		setLayout(new BorderLayout(0, 0));
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnComprar = new JButton("AÑADIR A LA CESTA");
		btnComprar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "El alimento se ha añadido correctamente la cesta");
				BD.alimentoReservado(con, al.getNombre());
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new FileWriter("cesta.txt"));
					ArrayList<Alimentos> alAlimentosEnCesta = BD.obtenerAlimentos(con);
					for(Alimentos ali: alAlimentosEnCesta) {
						if(ali.isEnCesta() == true) { //cambio isEnCesta
							String nombre = ali.getNombre();
							int precio = ali.getPrecio();
							String animal_dirigido = ali.getAnimal_dirigido();
							
							pw.println(nombre + ","+ precio + ","+ animal_dirigido); 
						}
					}} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally { 
					if(pw!=null) {
						pw.flush();
						pw.close();
					}
				}
				
			}
		});
		panelSur.add(btnComprar);
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(5, 0, 0, 0));
		panelDerecha.setPreferredSize(new Dimension(200,200));
		
		JLabel lblNombre = new JLabel("Nombre: "+al.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio: "+al.getPrecio()+ "€");
		lblPrecio.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPrecio);
		
		JLabel lblAnimalDirigido = new JLabel("Animal dirigido: "+al.getAnimal_dirigido());
		lblAnimalDirigido.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblAnimalDirigido);
		
		//CAMBIO NUEVO LABEL
		JLabel lblFechaCaducidad = new JLabel("Fecha de caducidad: "+al.getFechaCaducidad());
		lblFechaCaducidad.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblFechaCaducidad);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(200, 200));
		add(panelCentro, BorderLayout.CENTER);
		
		ImageIcon im = new ImageIcon(al.getRutaFoto());
		im.setDescription(al.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
	}

}
