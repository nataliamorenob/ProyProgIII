package Datos;

public class Productos{
	protected String nombre;
	protected int precio;
	protected String animal_dirigido; //para qué animal es ese accesorio
	protected String rutaFoto;
	protected boolean enCesta; //cuando un accesorio o alimento se ha añadido a la cesta se pone a 1 
	protected int unidades;
	
	public Productos() {
		super();
	}
	public Productos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta, int unidades) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
		this.rutaFoto = rutaFoto;
		this.enCesta = enCesta;
		this.unidades = unidades;
	}
	
	public Productos(String nombre, int precio, String animal_dirigido) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getAnimal_dirigido() {
		return animal_dirigido;
	}

	public void setAnimal_dirigido(String animal_dirigido) {
		this.animal_dirigido = animal_dirigido;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	
	public boolean isEnCesta() {
		return enCesta;
	}

	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public void setEnCesta(boolean enCesta) {
		this.enCesta = enCesta;
	}


}
