
public class Perros extends Animales implements Adoptable{
	private int tiempoEnAdopcion; //el timepo que ha estado en la protectora
	private int localizacion;
	private Perros() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	private Perros(String nombre, int edad, String sexo, int peso, int altura, String caracteristicas,int tiempoEnAdopcion, int localizacion) {
		super(nombre, edad, sexo, peso, altura, caracteristicas);
		this.tiempoEnAdopcion = tiempoEnAdopcion;
		this.localizacion = localizacion;
	}



	public int getTiempoEnAdopcion() {
		return tiempoEnAdopcion;
	}
	public void setTiempoEnAdopcion(int tiempoEnAdopcion) {
		this.tiempoEnAdopcion = tiempoEnAdopcion;
	}
	public int getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(int localizacion) {
		this.localizacion = localizacion;
	}
	
	
	

	
	
	
	

	

	
	
	
	
}
