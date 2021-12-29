package proyecto_clases;
import java.awt.Font;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.multi.MultiLabelUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PanelQuienesSomos extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel base = new JPanel();
	private JPanel lPanel = new JPanel();
	private JPanel rPanel = new JPanel();
	
	public PanelQuienesSomos() {
		
		add(base);
		lPanel.setPreferredSize(new Dimension(400, 400));
		lPanel.setLayout(new BorderLayout());

		//Label for the left panel
		JLabel LblIcon = new JLabel();
		LblIcon.setIcon(new ImageIcon("FOTOS/logo.jpg"));
		lPanel.add(LblIcon, BorderLayout.WEST);
		
		//Labels for the text
		rPanel.setLayout(new BorderLayout());
		EmptyBorder b =new EmptyBorder(100, 100, 100, 100);
		rPanel.setBorder(b);
		
		JLabel title = new JLabel("¿QUIENES SOMOS?");
		title.setFont(new Font("verdana", Font.BOLD, 30));
		rPanel.add(title);
		
		
		JLabel text = new JLabel("<html>Somos una pequeña asociación sin ánimo de lucro fromada por voluntarios y socios, <br/> "
								+ "cuyo único objetivo es poder beneficiar a todos aquellos animales que no tienen hogar. <br/ "
								+ "A parte de buscar una famialia a todos aquellos perro y gatos de Bizkaia, también contamos <br/>"
								+ "con un programa de protección y santuario de toda la fauna aut�ctona que por algún motivo <br/>"
								+ "se ha quedado desplazada de su h�bitat se recupera en nuestras instalaciones hasta su posible <br/>"
								+ "reinsercción."
								+ "<br/> <br/> En nuestra web podrás encontrar contribuir mediante la compra de alimentos y accesorios <br/>"
								+"para tu mascota o, sencillamente, encontar al compañero que estabas buscando. <br/>"
								+ "</html>");
		text.setFont(new Font("monospaced", Font.PLAIN, 15));
		
		rPanel.add(text, BorderLayout.AFTER_LAST_LINE);
		
		base.add(lPanel, BorderLayout.WEST);
		base.add(rPanel, BorderLayout.EAST);
	}
}

