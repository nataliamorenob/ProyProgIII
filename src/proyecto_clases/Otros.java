package proyecto_clases;

public class Otros extends Animales implements Visitables {
	private int estimacionAnyo;
	private boolean peligroExtincion;
	
	
	
	private Otros() {
		super();	
	}



	public Otros(String nombre, int edad, String sexo, int peso, String caracteristicas, int estimacionAnyo, boolean peligroExtincion,String rutaFoto) {
		super(nombre, edad, sexo, peso, caracteristicas,rutaFoto);
		this.estimacionAnyo = estimacionAnyo;
		this.peligroExtincion = peligroExtincion;
	}



	public int getEstimacionAnyo() {
		return estimacionAnyo;
	}



	public void setEstimacionAnyo(int estimacionAnyo) {
		this.estimacionAnyo = estimacionAnyo;
	}



	public boolean isPeligroExtincion() {
		return peligroExtincion;
	}



	public void setPeligroExtincion(boolean peligroExtincion) {
		this.peligroExtincion = peligroExtincion;
	}

	
	
	

}
