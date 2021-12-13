package proyecto_clases;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.FlowLayout;

public class PanelPerros extends JPanel {
	private Connection con;

	/**
	 * Create the panel.
	 */
	public PanelPerros(Perros p) {
		
		ImageIcon im = new ImageIcon(p.getRutaFoto());
		im.setDescription(p.getRutaFoto());
		JLabel lbl = new JLabel(im);
		add(lbl);
		JLabel lbl2 = new JLabel(p.getNombre());
		add(lbl2);
		updateUI();
		

	}
	
	
}

