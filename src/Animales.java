
public class Animales {
	protected String nombre;
	protected int edad;
	protected String sexo;
	protected int peso;
	protected int altura;
	protected String caracteristicas;
	protected Animales() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected Animales(String nombre, int edad, String sexo, int peso, int altura, String caracteristicas) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.caracteristicas = caracteristicas;
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
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	
	
	

	
	
	
	
	

}
