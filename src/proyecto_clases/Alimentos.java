package proyecto_clases;


public class Alimentos {
	private String nombre;
	private int precio;
	private String animal_dirigido; //para que animal es ese alimento
	private String rutaFoto;
	private boolean aliEnCesta; //cuando un alimento se ha añadido a la cesta se pone a 1 
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto, boolean aliEnCesta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
		this.rutaFoto = rutaFoto;
		this.setAliEnCesta(aliEnCesta);
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
	public boolean isAliEnCesta() {
		return aliEnCesta;
	}
	public void setAliEnCesta(boolean aliEnCesta) {
		this.aliEnCesta = aliEnCesta;
	}
	
	
	
	
	
	

}

