package Administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BaseDeDatos.BD;
import Datos.Compra;
import Datos.Reserva;

public class PanelReservasAdmin extends JPanel {
	
	private JTable tablaReservas;
	private DefaultTableModel modeloTablaReservas;
	private JTableHeader th;
	private ArrayList<Reserva> alReservas;

	/**
	 * Create the panel.
	 */
	public PanelReservasAdmin() {
		
		Connection con;
		con=BD.initBD("BaseDatos.db");
		setLayout(new BorderLayout(0, 0));
		
		alReservas = BD.obtenerReservas(con);
		BD.closeBD();
		
		String [] titulos = {"NombreUsuario", "TipoAnimal", "NombreAnimal"};  
	       modeloTablaReservas = new DefaultTableModel() {
	       	//Para que la tabla no pueda ser editada
	       	public boolean isCellEditable(int row, int column) {
	   			return false;
	   		}
	       };
	       
	    modeloTablaReservas.setColumnIdentifiers(titulos);  
	      
	    for(Reserva r: alReservas) {
	   	 String [] fila = {r.getUsuario(), r.getAnimal(), r.getNombreAnimal()};
	   	 modeloTablaReservas.addRow(fila);
	    }
	    
	    tablaReservas = new JTable(modeloTablaReservas);
	    JScrollPane scrollTabla = new JScrollPane(tablaReservas);
		add(scrollTabla);
		
		th = tablaReservas.getTableHeader();
		th.setFont(new Font("Bell MT", Font.PLAIN, 12));
		th.setBackground(Color.WHITE);
		
		tablaReservas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				DefaultTableCellRenderer modeloCnt = new DefaultTableCellRenderer();
				modeloCnt.setHorizontalAlignment(CENTER);
				tablaReservas.getColumnModel().getColumn(0).setCellRenderer(modeloCnt);
				tablaReservas.getColumnModel().getColumn(1).setCellRenderer(modeloCnt);
				tablaReservas.getColumnModel().getColumn(2).setCellRenderer(modeloCnt);
				return c;
			}
		});
	
	}

}
