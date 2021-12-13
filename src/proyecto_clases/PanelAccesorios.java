package proyecto_clases;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAccesorios extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAccesorios() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("ACCESORIOS PARA NUESTRAS MASCOTAS");
		lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		add(lblNewLabel);	
	}

}
