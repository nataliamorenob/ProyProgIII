package proyecto_clases;

import java.util.regex.Pattern;

public class Usuario {
	private String usuario;
	private String contrasenia; //El formato deber� tener 6 letras, de las cuales una ser� may�scula, y un d�gito.
	
	public Usuario(String usuario, String contrasenia) {
		this.usuario = usuario;
		String ercontrasenia = "[A-Z][a-z]{5}[0-9]";
		boolean correcto = Pattern.matches(ercontrasenia, contrasenia);
		if(correcto) {
			this.contrasenia = contrasenia;
		}else {
			System.out.println("La contrase�a no es correcta");
		}
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
