package proyecto_clases;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class PanelQuienesSomos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelQuienesSomos() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("SOBRE NOSOTROS");
		lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		add(lblNewLabel);
	}

}

