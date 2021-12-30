package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaCesta extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaCesta;
	private JTable tablaProductos; //cambio
	private DefaultTableModel modeloTablaProductos;  //cambio
	private TreeSet<Productos> tsProductos; //cambio

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCesta frame = new VentanaCesta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCesta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("CESTA DE LA COMPRA");
		ImageIcon im = new ImageIcon("FOTOS/logo.jpg");
		this.setIconImage(im.getImage());
		ventanaCesta = this;
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		//cambio
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		JButton btnVolver = new JButton("Volver a Menu");
		btnVolver.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnVolver);
		
		JButton btnComprar =new JButton("Comprar");
		btnComprar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnComprar);
		
		JButton btnTicket =new JButton("Generar ticket");
		btnTicket.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnTicket);
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMenu();
				ventanaCesta.setVisible(false);
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		 //cambio
		
		//Leer el fichero cesta
				File fichero = null;
			    FileReader fr = null;
			    BufferedReader br = null;
			    TreeSet<Productos> tsProductos = new TreeSet();
		        try {
		        	br = new BufferedReader(new FileReader("cesta.txt"));
		        	//fichero = new File ("cesta.txt");
					//fr = new FileReader (fichero);
					
					String linea = br.readLine();
					while(linea!=null) {
						String [] datos = linea.split(",");
						String nombre = datos[0];
						Integer precio = Integer.parseInt(datos[1]);
						String animal_dirigido = datos[2];
						Productos pr = new Productos(nombre, precio, animal_dirigido); 
						tsProductos.add(pr);
						linea = br.readLine();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(br!=null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		        
		        String [] columnas = {"Nombre","Precio","Animal Dirigido"};  
		        modeloTablaProductos = new DefaultTableModel();
		        modeloTablaProductos.setColumnIdentifiers(columnas);
				
				for(Productos p: tsProductos) {
					String dataRow[] = {p.getNombre(), String.valueOf(p.getPrecio()), p.getAnimal_dirigido()}; //CAMBIO FALTA LA FECHA DE CADUCIDAD
					modeloTablaProductos.addRow(dataRow);
				}
				tablaProductos = new JTable(modeloTablaProductos);
				JScrollPane scrollTabla = new JScrollPane(tablaProductos);
				panelCentro.add(scrollTabla);
				
				
		setVisible(true);
	}

}
