
public class Gatos extends Animales implements Adoptable{
	private String colores;

	private Gatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Gatos(String nombre, int edad, String sexo, int peso, int altura, String caracteristicas, String colores) {
		super(nombre, edad, sexo, peso, altura, caracteristicas);
		// TODO Auto-generated constructor stub
		this.colores=colores;
	}

	private Gatos(String colores) {
		super();
		this.colores = colores;
	}

	public String getColores() {
		return colores;
	}

	public void setColores(String colores) {
		this.colores = colores;
	}

	

	
	



	 
	 


}
