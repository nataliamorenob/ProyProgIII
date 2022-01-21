package Datos;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Alimentos extends Productos{
	private String fechaCaducidad;


	public Alimentos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta, int unidades) {
		super(nombre, precio, animal_dirigido, rutaFoto, enCesta, unidades);
		// TODO Auto-generated constructor stub
	}
	
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta, int unidades,
			String fechaCaducidad) {
		super(nombre, precio, animal_dirigido, rutaFoto, enCesta, unidades);
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
	
	
	
		

}

