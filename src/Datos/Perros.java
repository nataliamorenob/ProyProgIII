package Datos;

public class Perros extends Animales implements Adoptable{
	private int tiempoEnAdopcion; //el tiempo que ha estado en la protectora en MESES
	private String localizacion;
	private String colores;
	private boolean reservado;
	//private String usuario; //El nick del usuario que haga la reserva
	
	
	public Perros() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Perros(String nombre, int edad, String sexo, int peso, String caracteristicas,int tiempoEnAdopcion, String localizacion, String colores, boolean reservado, String rutaFoto){
		super(nombre, edad, sexo, peso, caracteristicas,rutaFoto);
		this.tiempoEnAdopcion = tiempoEnAdopcion;
		this.localizacion = localizacion;
		this.colores=colores;
		this.reservado = reservado;	
	}
	

	/*public Perros(String nombre, int edad, String sexo, int peso, String caracteristicas,int tiempoEnAdopcion, String localizacion, String colores, boolean reservado, String rutaFoto, String usuario){
		super(nombre, edad, sexo, peso, caracteristicas,rutaFoto);
		this.tiempoEnAdopcion = tiempoEnAdopcion;
		this.localizacion = localizacion;
		this.colores=colores;
		this.reservado = reservado;
		this.usuario = usuario;
	}*/

	/*private Perros(int tiempoEnAdopcion, String localizacion,String colores, boolean reservado, String usuario) {
		super();
		this.tiempoEnAdopcion=tiempoEnAdopcion;
		this.localizacion = localizacion;
		this.colores = colores;
		this.reservado = reservado;
		this.usuario=usuario;
		
	}*/
	

	

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
	

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	

	/*public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}*/
	
	
}
