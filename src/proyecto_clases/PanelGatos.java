package proyecto_clases;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelGatos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelGatos() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("GATOS");
		lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		add(lblNewLabel);

	}

}
