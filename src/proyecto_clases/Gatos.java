package proyecto_clases;

public class Gatos extends Animales implements Adoptable{
	private int tiempoEnAdopcion; //el timepo que ha estado en la protectora
	private String localizacion;
	private String colores;

	public Gatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gatos(String nombre, int edad, String sexo, int peso, String caracteristicas,int tiempoEnAdopcion, String localizacion, String colores,String rutaFoto){
		super(nombre, edad, sexo, peso, caracteristicas,rutaFoto);
		// TODO Auto-generated constructor stub
		this.tiempoEnAdopcion=tiempoEnAdopcion;
		this.localizacion = localizacion;
		this.colores=colores;
	}

	private Gatos(int tiempoEnAdopcion, String localizacion,String colores) {
		super();
		this.tiempoEnAdopcion=tiempoEnAdopcion;
		this.localizacion = localizacion;
		this.colores = colores;
		
	}

	public int getTiempoEnAdopcion() {
		return tiempoEnAdopcion;
	}

	public void setTiempoEnAdopcion(int tiempoEnAdopcion) {
		this.tiempoEnAdopcion = tiempoEnAdopcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getColores() {
		return colores;
	}

	public void setColores(String colores) {
		this.colores = colores;
	}

	

	

	
	



	 
	 


}
