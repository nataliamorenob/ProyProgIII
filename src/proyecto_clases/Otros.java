package proyecto_clases;

public class Otros extends Animales implements Visitables {
	private boolean peligroExtincion;

	
	private Otros() {
		super();	
	}



	public Otros(String nombre, int edad, String sexo, int peso, String caracteristicas, boolean peligroExtincion,String rutaFoto) {
		super(nombre, edad, sexo, peso, caracteristicas,rutaFoto);
		this.peligroExtincion = peligroExtincion;
	}


	public boolean isPeligroExtincion() {
		return peligroExtincion;
	}



	public void setPeligroExtincion(boolean peligroExtincion) {
		this.peligroExtincion = peligroExtincion;
	}

	
	
	

}
