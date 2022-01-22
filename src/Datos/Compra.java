package Datos;

public class Compra {
	
	private String nombreUsuario;
	private String nombreProducto;
	private int unidadProducto;
	
	public Compra(String nombreUsuario, String nombreProducto, int unidadProducto) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombreProducto = nombreProducto;
		this.unidadProducto = unidadProducto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
