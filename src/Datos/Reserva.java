package Datos;

public class Reserva {
	
	private String usuario;
	private String animal;
	private String nombreAnimal;
	
	public Reserva(String usuario, String animal, String nombreAnimal) {
		super();
		this.usuario = usuario;
		this.animal = animal;
		this.nombreAnimal = nombreAnimal;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getNombreAnimal() {
		return nombreAnimal;
	}

	public void setNombreAnimal(String nombreAnimal) {
		this.nombreAnimal = nombreAnimal;
	}
	
	
}
