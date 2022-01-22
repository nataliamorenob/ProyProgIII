package Datos;

public class Compra {
	
	private String nombreUsuario;
	private String nombreAnimal;
	private String nombreProducto;
	private int unidadProducto;
	
	public Compra(String nombreUsuario, String nombreAnimal, String nombreProducto, int unidadProducto) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombreAnimal = nombreAnimal;
		this.nombreProducto = nombreProducto;
		this.unidadProducto = unidadProducto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreAnimal() {
		return nombreAnimal;
	}

	public void setNombreAnimal(String nombreAnimal) {
		this.nombreAnimal = nombreAnimal;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getUnidadProducto() {
		return unidadProducto;
	}

	public void setUnidadProducto(int unidadProducto) {
		this.unidadProducto = unidadProducto;
	}
	
	
}
