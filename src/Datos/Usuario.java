package Datos;

import java.util.regex.Pattern;

public class Usuario {
	private String usuario;
	private String contrasenia; //El formato deberá tener 6 letras, de las cuales una será mayúscula, y un dígito.
	
	public Usuario(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
