package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;




public class VentanaCesta extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaCesta;
	private JTable tablaProductos; 
	private DefaultTableModel modeloTablaProductos;  

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
	@SuppressWarnings("serial")
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
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		JLabel lblCesta = new JLabel("Productos en cesta");
		lblCesta.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
		panelNorte.add(lblCesta);
		
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
				
		//Leer el fichero cesta
				File fichero = null;
			    FileReader fr = null;
			    BufferedReader br = null;
			    ArrayList<Productos> tsProductos = new ArrayList();

		        try {
		        	br = new BufferedReader(new FileReader("cesta.txt"));
					String linea = br.readLine();
					while(linea!=null) {
						String [] datos = linea.split(",");
						String nombre = datos[0];
						Integer precio = Integer.parseInt(datos[1]);
						String animal_dirigido = datos[2];
						Productos pr = new Productos(nombre, precio, animal_dirigido); 
						tsProductos.add(pr);
						//System.out.println(tsProductos);
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
		        
		        btnComprar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				
				btnTicket.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						mostrarTicket(tsProductos);
					}
				});

		        
		        String [] columnas = {"Nombre","Precio","Animal Dirigido"};  
		        modeloTablaProductos = new DefaultTableModel() {
		        	public boolean isCellEditable(int row, int column) {
		    			return false;
		    		}
		        };

		        modeloTablaProductos.setColumnIdentifiers(columnas);
				
				for(Productos p: tsProductos) {
					String dataRow[] = {p.getNombre(), String.valueOf(p.getPrecio()), p.getAnimal_dirigido()}; //CAMBIO FALTA LA FECHA DE CADUCIDAD
					modeloTablaProductos.addRow(dataRow);
				}
				
				tablaProductos = new JTable(modeloTablaProductos);
				JScrollPane scrollTabla = new JScrollPane(tablaProductos);
				panelCentro.add(scrollTabla);
				
				JTableHeader th;
				th = tablaProductos.getTableHeader();
				Font fuente = new Font("Bell MT", Font.PLAIN, 12);
				th.setFont(fuente);
				th.setBackground(Color.WHITE);
				
				tablaProductos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {
						Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						DefaultTableCellRenderer modeloCnt = new DefaultTableCellRenderer();
						modeloCnt.setHorizontalAlignment(CENTER);
						tablaProductos.getColumnModel().getColumn(0).setCellRenderer(modeloCnt);
						tablaProductos.getColumnModel().getColumn(1).setCellRenderer(modeloCnt);
						tablaProductos.getColumnModel().getColumn(2).setCellRenderer(modeloCnt);
						return c;
					}
				});
				

				
		setVisible(true);
	}
	
//	public static void mostrarTicket(ArrayList<Productos> productos) {
//		System.out.println("PROTECTORA");
//	    System.out.println("-".repeat(64));
//	    System.out.printf("|%-20s|%-20s|%-20s|\n", "Descripción", "Precio", "Animal Dirigido");
//	    System.out.println("-".repeat(64));
//	    double total = 0;
//	    for (Productos p : productos) {
//	        System.out.printf("|%-20s|%-20s|%-20s|\n", p.getNombre(), p.getPrecio(), p.getAnimal_dirigido());
//	        total += p.getPrecio();
//	    }
//	    System.out.println("-".repeat(64));
//	    System.out.printf("|%62s|\n", "Total: " + String.valueOf(total));
//	    System.out.println("                    **Gracias por su compra**");
//	}

}
