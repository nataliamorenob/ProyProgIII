package Datos;

public class Productos implements Comparable{
	protected String nombre;
	protected int precio;
	protected String animal_dirigido; //para qué animal es ese accesorio
	protected String rutaFoto;
	protected boolean enCesta; //cuando un accesorio o alimento se ha añadido a la cesta se pone a 1 
	
	
	public Productos() {
		super();
	}
	public Productos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
		this.rutaFoto = rutaFoto;
		this.enCesta = enCesta;
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

	public void setEnCesta(boolean enCesta) {
		this.enCesta = enCesta;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
