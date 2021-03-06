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
import Datos.Alimentos;
import Ventanas.VentanaInicioSesion;

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
		
		
		
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int unidadesUsuario = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el numero de unidades que desea:"));
				int unidades=a.getUnidades()-unidadesUsuario;
				
				
				if(unidades==0) { //SI YA NO QUEDAN UNIDADES EN LA BBDD, ELIMINAMOS EL ACCESORIO
					JOptionPane.showMessageDialog(null, "El accesorio se ha añadido correctamente la cesta");
					BD.accesorioReservado(con, a.getNombre());
					BD.accesoriosUnidades(con, a.getNombre(), unidades);
					

					PrintWriter pw = null;
					try {
						pw = new PrintWriter(new FileWriter("cesta.txt", true));
						ArrayList<Accesorios> alAccesoriosEnCesta = BD.obtenerAccesorios(con);
						for(Accesorios acc: alAccesoriosEnCesta) {
							if(acc.isEnCesta() == true) { 
								String nombre = acc.getNombre();
								int precio = acc.getPrecio();
								System.out.println(precio);
								String animal_dirigido = acc.getAnimal_dirigido();
								pw.println(nombre + ","+ precio + ","+ animal_dirigido + "," + unidadesUsuario); 
								BD.anyadirCompra(con, VentanaInicioSesion.usu, nombre, unidadesUsuario);
								BD.accesoriosACero(con);
		
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
					BD.borrarAccesorios(con, a.getNombre());
					panelCentro.removeAll();
					BD.obtenerAccesorios(con);
					
				}
				else if(unidades<0) {
					JOptionPane.showMessageDialog(null, "No quedan tantas unidades disponsibles, porfavor introduzca otro número de unidades:");
	
				}else {
					BD.accesorioReservado(con, a.getNombre());
					BD.accesoriosUnidades(con, a.getNombre(), unidades);
					PrintWriter pw=null;
					try {
						pw = new PrintWriter(new FileWriter("cesta.txt", true));
						ArrayList<Accesorios> alAccesoriosEnCesta = BD.obtenerAccesorios(con);
						for(Accesorios acc: alAccesoriosEnCesta) {
							if(acc.isEnCesta() == true) { 
								String nombre = acc.getNombre();
								int precio = acc.getPrecio();
								String animal_dirigido = acc.getAnimal_dirigido();
								pw.println(nombre + ","+ precio + ","+ animal_dirigido + "," + unidadesUsuario); 
								BD.anyadirCompra(con, VentanaInicioSesion.usu, nombre, unidadesUsuario);
								BD.accesoriosACero(con);
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
					
				}


			}
		});
		panelSur.add(btnComprar);

	}
  
}
