package proyecto_clases;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.awt.FlowLayout;

public class PanelPerros extends JPanel {
	private Connection con;

	/**
	 * Create the panel.
	 */
	public PanelPerros() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("PERROS");
		lblNewLabel.setFont(new Font("Bell MT", Font.PLAIN, 11));
		add(lblNewLabel);
		con = BD.initBD("BaseDatos.db");
		BD.obtenerPerros(con);

	}

}

