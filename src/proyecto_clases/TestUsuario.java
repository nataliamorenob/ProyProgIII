package proyecto_clases;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUsuario {

	@Test
	public void test() {
		Usuario u = new Usuario("Natalia", "Natali7");
		assertTrue(u.getContrasenia().equals("Natali7"));
	}

	//HOLA
}
