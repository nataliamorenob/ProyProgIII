package Administrador;

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

import BaseDeDatos.BD;
import Datos.Otros;

public class PanelOtrosAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelOtrosAdmin(Otros o) {
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BD.borrarOtros(con, o.getNombre());
				panelCentro.removeAll();
				BD.obtenerPerros(con);
			}
		});

		panelSur.add(btnEliminar);
		
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setLayout(new GridLayout(6, 0, 0, 0));
		panelDerecha.setPreferredSize(new Dimension(200, 200));
		
		JLabel lblNombre = new JLabel("Nombre: "+o.getNombre());
		lblNombre.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad: "+o.getEdad());
		lblEdad.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblEdad);
		
		JLabel lblSexo = new JLabel("Sexo: "+o.getSexo());
		lblSexo.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblSexo);
		
		JLabel lblPeso = new JLabel("Peso: "+o.getPeso());
		lblPeso.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblPeso);
		
		JLabel lblCaracteristica = new JLabel("Carac: "+o.getCaracteristicas());
		lblCaracteristica.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblCaracteristica);
		
		JLabel lblExt = new JLabel("Peligro Extincion: "+o.isPeligroExtincion());
		lblExt.setFont(new Font("Bell MT", Font.PLAIN, 11));
		panelDerecha.add(lblExt);
		
		
		ImageIcon im = new ImageIcon(o.getRutaFoto());
		im.setDescription(o.getRutaFoto());
		JLabel lbLabelFoto = new JLabel(im);
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
		lbLabelFoto.setIcon(imagenConDimensiones); 
		panelCentro.add(lbLabelFoto);
	}

}
