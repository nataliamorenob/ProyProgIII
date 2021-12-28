package proyecto_clases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class TestBaseDatos {
	
	@Before
	public void before() {
		BD.initBD("BaseDatos.db");
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

	
	
	
}
