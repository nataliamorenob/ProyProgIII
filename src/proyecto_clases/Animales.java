package proyecto_clases;

public class Animales {
	protected String nombre;
	protected int edad;
	protected String sexo;
	protected int peso;
	protected String caracteristicas;
	private String rutaFoto;

	protected Animales() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected Animales(String nombre, int edad, String sexo, int peso, String caracteristicas,String rutaFoto){
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.peso = peso;
		this.caracteristicas = caracteristicas;
		this.rutaFoto=rutaFoto;
;

	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getRutaFoto() {
		return rutaFoto;
	}
	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	
	
	
	
	

	
	
	
	
	

}
