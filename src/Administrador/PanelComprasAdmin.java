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

public class PanelComprasAdmin extends JPanel {
	
	private JTable tablaCompras;
	private DefaultTableModel modeloTablaCompras;
	private JTableHeader th;
	private ArrayList<Compra> alCompras;
	
	/**
	 * Create the panel.
	 */
	public PanelComprasAdmin() {
		Connection con;
		con=BD.initBD("BaseDatos.db");
		setLayout(new BorderLayout(0, 0));
		
		alCompras = BD.obtenerCompras(con);
		BD.closeBD();
		
		String [] titulos = {"NombreUsuario","TipoAnimal","NombreAnimal"};  
	       modeloTablaCompras = new DefaultTableModel() {
	       	//Para que la tabla no pueda ser editada
	       	public boolean isCellEditable(int row, int column) {
	   			return false;
	   		}
	       };
	       
	    modeloTablaCompras.setColumnIdentifiers(titulos);  
	      
	    for(Compra c: alCompras) {
	   	 String [] fila = {c.getNombreUsuario(), c.getNombreProducto(), String.valueOf(c.getUnidadProducto())};
	   	 modeloTablaCompras.addRow(fila);
	    }
	    
	    tablaCompras = new JTable(modeloTablaCompras);
	    JScrollPane scrollTabla = new JScrollPane(tablaCompras);
		add(scrollTabla);
		
		th = tablaCompras.getTableHeader();
		th.setFont(new Font("Bell MT", Font.PLAIN, 12));
		th.setBackground(Color.WHITE);
		
		tablaCompras.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				DefaultTableCellRenderer modeloCnt = new DefaultTableCellRenderer();
				modeloCnt.setHorizontalAlignment(CENTER);
				tablaCompras.getColumnModel().getColumn(0).setCellRenderer(modeloCnt);
				tablaCompras.getColumnModel().getColumn(1).setCellRenderer(modeloCnt);
				tablaCompras.getColumnModel().getColumn(2).setCellRenderer(modeloCnt);
				return c;
			}
		});
	}

}
