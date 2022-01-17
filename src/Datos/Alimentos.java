package Datos;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Alimentos extends Productos{
	private String fechaCaducidad;


	public Alimentos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta) {
		super(nombre, precio, animal_dirigido, rutaFoto, enCesta);
		// TODO Auto-generated constructor stub
	}
	
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta,
			String fechaCaducidad) {
		super(nombre, precio, animal_dirigido, rutaFoto, enCesta);
		this.fechaCaducidad = fechaCaducidad;
	}

	public Alimentos(String fechaCaducidad) {
		super();
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	//private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		

}

