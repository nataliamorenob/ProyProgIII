package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import BaseDeDatos.BD;
import Datos.Usuario;

import java.sql.SQLException;

public class Tests {

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
	
	
	
	
}
