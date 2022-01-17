package Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BaseDeDatos.BD;
import Datos.Accesorios;

public class PanelAccesorios extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAccesorios(Accesorios a) {
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
				JOptionPane.showMessageDialog(null, "El accesorio se ha añadido correctamente la cesta");
				BD.accesorioReservado(con, a.getNombre());
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new FileWriter("cesta.txt"));
					ArrayList<Accesorios> alAccesoriosEnCesta = BD.obtenerAccesorios(con);
					for(Accesorios acc: alAccesoriosEnCesta) {
						if(acc.isEnCesta() == true) { //cambio isEnCesta -> antes acc.isAcEnCesta
							String nombre = acc.getNombre();
							int precio = acc.getPrecio();
							String animal_dirigido = acc.getAnimal_dirigido();
							
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
		
		JLabel lblNombre = new JLabel("Nombre: "+a.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio: "+a.getPrecio()+"€");
		lblPrecio.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPrecio);
		
		JLabel lblAnimalDirigido = new JLabel("Animal dirigido: "+a.getAnimal_dirigido());
		lblAnimalDirigido.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblAnimalDirigido);
		

		
		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(200, 200));
		add(panelCentro, BorderLayout.CENTER);
		
		ImageIcon im = new ImageIcon(a.getRutaFoto());
		im.setDescription(a.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
		
		//JLabel lblNewLabel = new JLabel("ACCESORIOS PARA NUESTRAS MASCOTAS");
		//lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		//add(lblNewLabel);	
	}
  
}