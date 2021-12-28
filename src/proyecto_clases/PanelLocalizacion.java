package proyecto_clases;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PanelLocalizacion extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private Connection con; //CAMBIO  
	public PanelLocalizacion() {
		
		//Connection con; //CAMBIO
		//con = BD.initBD("BaseDatos.db"); //CAMBIO
		
		
		JPanel panel = new JPanel();
		add(panel);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		//JLabel lblNewLabel = new JLabel("NUESTRAS LOCALIZACIONES");
		//lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		//add(lblNewLabel);
		
		
		
		// PRINCIPIO CAMBIO
		JMenuBar jm1 = new JMenuBar();
	
		JMenu perros = new JMenu("PERROS");
		JMenuItem jmiBeasainP = new JMenuItem("BEASAIN");
		JMenuItem jmiGetxoP = new JMenuItem("GETXO");
		JMenuItem jmiVitoriaP = new JMenuItem("VITORIA");
		
		
		JMenu gatos = new JMenu("GATOS");
		JMenuItem jmiBeasainG = new JMenuItem("BEASAIN");
		JMenuItem jmiGetxoG = new JMenuItem("GETXO");
		JMenuItem jmiVitoriaG = new JMenuItem("VITORIA");
		
		panel.add(jm1);
		jm1.add(perros);
		jm1.add(gatos);
		
		perros.add(jmiBeasainP);
		perros.add(jmiGetxoP);
		perros.add(jmiVitoriaP);
		
		gatos.add(jmiBeasainG);
		gatos.add(jmiGetxoG);
		gatos.add(jmiVitoriaG);
		
		jmiBeasainG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Gatos> alGatos = BD.obtenerGatosBeasain(con, "Beasain");
				for(Gatos g: alGatos) {
					panel.add(new PanelGatos(g));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		jmiVitoriaG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Gatos> alGatos = BD.obtenerGatosVitoria(con, "Vitoria");
				for(Gatos g: alGatos) {
					panel.add(new PanelGatos(g));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		jmiGetxoG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Gatos> alGatos = BD.obtenerGatosGetxo(con, "Getxo");
				for(Gatos g: alGatos) {
					panel.add(new PanelGatos(g));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		jmiBeasainP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> alPerros = BD.obtenerPerrosBeasain(con, "Beasain");
				for(Perros p: alPerros) {
					panel.add(new PanelPerros(p));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		jmiVitoriaP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> alPerros = BD.obtenerPerrosVitoria(con, "Vitoria");
				for(Perros p: alPerros) {
					panel.add(new PanelPerros(p));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		jmiGetxoP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); 
				Connection con = BD.initBD("BaseDatos.db");
				ArrayList<Perros> alPerros = BD.obtenerPerrosGetxo(con, "Getxo");
				for(Perros p: alPerros) {
					panel.add(new PanelPerros(p));
				}
				BD.closeBD();
				panel.updateUI();
			}
		});
		
		
	}
	
}
