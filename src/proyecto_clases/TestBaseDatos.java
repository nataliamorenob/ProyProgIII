package proyecto_clases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestBaseDatos {
	private Connection con;
	
	@Before
	public void before() {
		con = BD.initBD("BaseDatos.db");
	}
	
	@After
	public void after() {
		BD.closeBD();
	}

	@Test
	//Expresiones regulares con nombre de usuario
	public void testUsuario() {
		Usuario u = new Usuario("Natalia", "Natali7");
		assertTrue(u.getContrasenia().equals("Natali7"));
	}
	
	
	@Test
	//NO SÉ SI ESTA BIEN???OSEA DA BIEN PERO NO SE SI TIENE SENTIDO
	//Comprueba que se haya abierto la conexión
	public void testAbrirConexion() {
		assertNotNull(BD.initBD("BaseDatos.db"));
	}
	

		
	/***
	* Test que comprueba si el método obtenerPerros de la BD es igual a 15.
	*/
	/*@Test
	public void testObtenerPerros() {
		ArrayList<Perros> alPerros=BD.obtenerPerros(Connection con);
		assertTrue(alPerros.size()==15);
	}*/
	
	@Test
	public void testObtenerPerros(){
		ArrayList<Perros> ap = BD.obtenerPerros(con);
		ArrayList<String> l = new ArrayList<String>();
		l.add("Beasain");
		l.add("Vitoria");
		l.add("Getxo");
		int neg = -1;
		int anyos = 20;
		for (Perros p : ap){
			assertTrue(l.contains(p.getLocalizacion()));
			assertTrue(p.getPeso() > neg);
			assertTrue(p.getTiempoEnAdopcion() > neg && p.getTiempoEnAdopcion() <= anyos );
			
		}
	}
	
}
