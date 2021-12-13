package proyecto_clases;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAlimentos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAlimentos() {
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("ALIMENTOS");
		lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		add(lblNewLabel);
	}

}
