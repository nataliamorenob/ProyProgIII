
public class Accesorios {
	private String nombre;
	private int precio;
	private String animal_dirigido; //para que animal es ese alimento
	
	private Accesorios(){
		super();
		
	}

	private Accesorios(String nombre, int precio, String animal_dirigido) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.animal_dirigido = animal_dirigido;
	}
	
	
}
