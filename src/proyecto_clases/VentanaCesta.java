package proyecto_clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.*;

public class VentanaCesta extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaCesta;
	private JTable tablaProductos; //cambio
	private DefaultTableModel modeloTablaProductos;  //cambio
	//private TreeSet<Productos> tsProductos; //cambio

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
		
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		JButton btnVolver = new JButton("Volver a Menu");
		btnVolver.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnVolver);
		
		JButton btnComprar =new JButton("Comprar");
		btnComprar.setFont(new Font("Bodoni MT", Font.PLAIN, 11));
		panelSur.add(btnComprar);
		
		
		
		
		//Leer el fichero cesta

		File fichero = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    ArrayList<Productos> tsProductos = new ArrayList();

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
        
        //TABLA LISTADO DE PRODUCTOS EN LA CESTA
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
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMenu();
				ventanaCesta.setVisible(false);
			}
		});
		
		//BOTONES--------------------------------------------------------------------------------------------------------------
		
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Edición JFrame
				JFrame jfPreguntas = new JFrame();
				jfPreguntas.setBackground(Color.BLUE);
				jfPreguntas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jfPreguntas.setBounds(500, 100, 600, 500);
				jfPreguntas.setVisible(true);	
				//Edición panel base
				JPanel base = new JPanel();
				base.setLayout(new BorderLayout());
				
				//Edición panel con las preguntas
				JPanel pPreguntas = new JPanel();
				pPreguntas.setLayout(new GridLayout(6,1));
				
				//Edición panel btn de confirmación
				JPanel pBtn = new JPanel();
				
				//Buttons
				JButton btnConfirmar = new JButton("Confirmar");
				
				
				//JLABELS
				JLabel lblNom = new JLabel("Nombre:");
				JLabel lblApe = new JLabel("Apellidos:");
				JLabel lblDir = new JLabel("Dirección:");
				JLabel lblCP = new JLabel("Código postal:");
				JLabel lblTlf = new JLabel("Número de teléfono:");
				JLabel lblTC = new JLabel("Tarjeta de crédito:");
				
				//JTEXTFIELDS
				JTextField txtNom = new JTextField();
				txtNom.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				JTextField txtApe = new JTextField();
				txtApe.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				JTextField txtDir = new JTextField();
				txtDir.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				JTextField txtCP = new JTextField();
				txtCP.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				JTextField txtTlf = new JTextField();
				txtTlf.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				JTextField txtTC = new JTextField();
				txtTC.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				
				//Campo de preguntas añadido al panel de preguntas
				pPreguntas.add(lblNom);
				pPreguntas.add(txtNom);
				pPreguntas.add(lblApe);
				pPreguntas.add(txtApe);
				pPreguntas.add(lblDir);
				pPreguntas.add(txtDir);
				pPreguntas.add(lblCP);
				pPreguntas.add(txtCP);
				pPreguntas.add(lblTlf);
				pPreguntas.add(txtTlf);
				pPreguntas.add(lblTC);
				pPreguntas.add(txtTC);
				
				//Boton añadido a su panel boton
				pBtn.add(btnConfirmar);

				//Añadir paneles a la base 
				base.add(pPreguntas, BorderLayout.CENTER);
				base.add(pBtn, BorderLayout.SOUTH);

				jfPreguntas.add(base);	
				
				//Action Listener del botón confirmar
				btnConfirmar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						JFrame jfTicket = new JFrame();
						jfTicket.setBounds(0, 0, 500, 500);
						
						
						//JTextArea donde se mostrar el 
						JTextArea taTicket = new JTextArea();
						taTicket.setVisible(true);
						jfTicket.add(taTicket);
						
						//Creación del fichero ticket 
						PrintWriter pw = null;
						
						//Fecha en la que se realiza el pedido
						SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
						Date date = new Date(System.currentTimeMillis());
						if(txtNom.getText().equals("") || txtApe.getText().equals("") || txtDir.getText().equals("") ||
							txtCP.getText().equals("") || txtTlf.getText().equals("")	) {
							JOptionPane.showMessageDialog(null, "Tiene que rellenar todos los campos");}
						else {
							jfTicket.setVisible(true);
							try {
								pw = new PrintWriter(new PrintWriter("ticket.txt"));
								pw.println("REFUGIO \n");
								pw.println("-".repeat(121));
								pw.println(formatter.format(date));
								pw.println("\n nº Pedido: 1234 \n");
								
								Integer total = 0;
								
								for(Productos c : tsProductos) {

										Integer precio = c.getPrecio();
										String nombreP = c.getNombre();
										pw.println(nombreP +" ".repeat(50) + precio + "€");	
										
										//total suma del precio de productos
										total += precio;	
 								}
								
								pw.println("TOTAL: "+ String.valueOf(total) + "€");
								
								
								pw.println("-".repeat(121));
								
								
								pw.println("Destinatario: " + txtNom.getText()  + " " + txtApe.getText() + ", " + txtDir.getText() + ", " + txtCP.getText() + "\n");
								pw.println("Su tlfno de contacto: " + txtTlf.getText() + "\n");
								String nTarjeta = txtTC.getText();// La tarjeta tiene 10 caracteres
								pw.println("Numero de tarjeta de pago: " + "*".repeat(6) + nTarjeta.charAt(6) + nTarjeta.charAt(7) + nTarjeta.charAt(8) + nTarjeta.charAt(9) + "\n");
								pw.print("-".repeat(121) + "\n");
								pw.println("GRACIAS POR SU COMPRA");
							} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
								e1.printStackTrace();
							}finally {
								if(pw!=null) {
									pw.flush();
									pw.close();
								}	
							}
						
						//Lee y muestra el fichero "Ticket" en el JTextArea
							try {
								taTicket.read(new BufferedReader(new FileReader("ticket.txt")), null);
								} catch (Exception e1) {
							// TODO: handle exception
									e1.printStackTrace();
								}
					
					}
					}
				});
				
			}		
			
		});
		
		
				

				
		setVisible(true);
	}

}