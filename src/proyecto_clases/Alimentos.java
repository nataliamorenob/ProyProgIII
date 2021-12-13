package proyecto_clases;


public class Alimentos {
	private String nombre;
	private int precio;
	private String animal_dirigido; //para que animal es ese alimento
	private String rutaFoto;
	
	public Alimentos(String nombre, int precio, String animal_dirigido, String rutaFoto) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
		this.rutaFoto = rutaFoto;
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
	
	
	
	
	
	

}

