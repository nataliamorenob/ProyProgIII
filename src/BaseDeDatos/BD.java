package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import Datos.Accesorios;
import Datos.Alimentos;
import Datos.Gatos;
import Datos.Otros;
import Datos.Perros;


public class BD {
	private static Connection con;

	/**
	 * Metodo que establece la conexion con la BBDD
	 * @param nombreBD <- El nombre de la BBDD
	 * @return con <- La conexion con la BBDD
	 */
	public static Connection initBD(String nombreBD ) {
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
		} catch (ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}

	/**
	 * Metodo que cierra la conexion con la BBDD
	 */
	public static void closeBD() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas(Connection con) {
 		String sent1 = "CREATE TABLE IF NOT EXISTS Perros(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, tiempoEnAdopcion Integer, localizacion String, colores String, rutaFoto String, reservado boolean)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Gatos(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, tiempoEnAdopcion Integer, localizacion String, colores String,rutaFoto String, reservado boolean)";
		String sent3 = "CREATE TABLE IF NOT EXISTS Otros(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, peligroExtincion boolean, rutaFoto String)";
		String sent4 = "CREATE TABLE IF NOT EXISTS Alimentos(nombre String, precio Integer, animal_dirigido String, rutaFoto String, enCesta boolean, unidades Integer, fechaCaducidad String)"; 
		String sent5 = "CREATE TABLE IF NOT EXISTS Accesorios(nombre String, precio Integer, animal_dirigido String, rutaFoto String, enCesta boolean, unidades Integer)"; 
		String sent6 = "CREATE TABLE IF NOT EXISTS Usuario(usuario String, contrasenia String)";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
			st.executeUpdate(sent3);
			st.executeUpdate(sent4);
			st.executeUpdate(sent5);
			st.executeUpdate(sent6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Metodo que devuelve un valor entero dependiendo de si el usuario existe
	 * @param usuario <- el nick del usuario con el que se registra
	 * @param contrasenia <- la contraseña con la que el usuario se registra
	 * @return resultado con valor:
	 * -  0 si no existe dicho usuario
	 * -  1 si existe el usuario pero la contraseña no es correcta
	 * -  2 si el usuario existe y la contraseña es correcta
	 */
	public static int cogerUsuario(Connection con, String usuario, String contrasenia){
		String resolucion="SELECT contrasenia FROM Usuario WHERE usuario ='"+usuario+"'";
		java.sql.Statement stat=null;
		int resultado=0;
		try {
			stat=con.createStatement();
			ResultSet resuls= stat.executeQuery(resolucion);
			if(resuls.next()) {
				if(resuls.getString("contrasenia").equals(contrasenia)) {
					resultado=2;
				}else {
					resultado=1;
				}
			}else {
				resultado=0;
			}
			resuls.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}finally {
					if(stat!=null) {
						try {
							stat.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		return resultado;	
	}
	
	/**
	 * Metodo para añadir un nuevo usuario a la BBDD
	 * @param usuario  <- el nick del usuario con el que se registrara 
	 * @param contrasenia <- la contraseña con la que el usuario se registra
	 */
	public static void anyadirUsuario(Connection con, String usuario, String contrasenia ) {
		String resolucion="INSERT INTO Usuario VALUES('"+usuario+"','"+contrasenia+"')";
		java.sql.Statement stat=null;
		try {
			stat=con.createStatement();
			stat.executeUpdate(resolucion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stat!=null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo que devuelve un entero dependiendo si el usuario con ese nick existe
	 * @param con <- La conexion con la Base de Datos
	 * @param usuario <- El nick del usuario
	 * @return existe con los valores:
	 * - 0 si no existe
	 * - 1 si existe
	 */
	public static int existeUsuario(Connection con, String usuario){
		String sent = "SELECT usuario FROM USUARIO WHERE usuario = '"+usuario+"';";
		java.sql.Statement st = null;
		ResultSet rs;
		int existe = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sent);
			while(rs.next())
				existe = 1;
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	
	/**
	 * Metodo que devuelve un ArrayList de todos los perros que hay en la BBDD
	 * @param con <- La conexion con la BBDD
	 * @return alPerros <- El ArrayList que contiene los perros
	 */
	public static ArrayList<Perros> obtenerPerros(Connection con) {
		ArrayList<Perros> alPerros=new ArrayList<>();
		Perros p = null;
		String sent = "SELECT * FROM PERROS";
		Statement st = null;
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) { 
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				
				p = new Perros(nombre,edad,sexo,peso,caracteristicas,tiempoEnAdopcion,localizacion,colores,reservado,rutaFoto);
				alPerros.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alPerros;
	}
	
	/**
	 * Metodo que modifica el valor booleano de reservado si el perro ha sido adoptado
	 * @param con <- Conexion con la BBDD
	 * @param nombre <- Nombre del perro que ha sido adoptado
	 */
	public static void perroReservado(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "UPDATE PERROS SET reservado = 1 WHERE nombre = '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que devuelve un ArrayList de todos los gatos que hay en la BBDD
	 * @param con <- La conexion con la BBDD
	 * @return alGatos <- El ArrayList con todos los gatos
	 */
	public static ArrayList<Gatos> obtenerGatos(Connection con){
		ArrayList<Gatos> alGatos=new ArrayList<>();
		Gatos g = null;
		String sent = "SELECT * FROM GATOS";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				g = new Gatos(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				alGatos.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alGatos;
	}
	
	/**
	 * Metodo que modifica el valor booleano de reservado si el gato ha sido adoptado
	 * @param con <- Conexion con la BBDD
	 * @param nombre <- Nombre del gato que ha sido adoptado
	 */
	public static void gatoReservado(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "UPDATE GATOS SET reservado = 1 WHERE nombre = '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que devuelve un ArrayList de todos los animales no adoptables (otros) que hay en la BBDD
	 * @param con <- La conexion con la BBDD
	 * @return alOtros <- El ArrayList que contiene otros
	 */
	public static ArrayList<Otros> obtenerOtros(Connection con){
		ArrayList<Otros> alOtros = new ArrayList<>();
		Otros o = null;
		String sent = "SELECT * FROM OTROS";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Boolean peligroExtincion = rs.getBoolean("PELIGROEXTINCION");
				String rutaFoto = rs.getString("rutaFoto");
				o = new Otros(nombre, edad, sexo, peso, caracteristicas, peligroExtincion, rutaFoto);
				alOtros.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alOtros;
	}
	
	/**
	 * Metodo que devuelve un ArrayList de tipo Alimentos con todos los alimentos que se encuentren en la BBDD
	 * @param con <- Conexion con la base de datos
	 * @return alAlimentos <- ArrayList que contiene todos los alimentos
	 */
	public static ArrayList<Alimentos> obtenerAlimentos(Connection con){
		ArrayList<Alimentos> alAlimentos = new ArrayList<>();
		Alimentos amt = null;
		String sent = "SELECT * FROM ALIMENTOS";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer precio = rs.getInt("PRECIO");
				String animalDirigido = rs.getString("ANIMAL_DIRIGIDO");
				String rutaFoto = rs.getString("rutaFoto");
				boolean enCesta = rs.getBoolean("EN_CESTA"); 
				int unidades=rs.getInt("unidades");
				String fechaCaducidad = rs.getString("FECHA_CADUCIDAD");
				amt = new Alimentos(nombre, precio, animalDirigido, rutaFoto, enCesta, unidades, fechaCaducidad); 
				alAlimentos.add(amt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alAlimentos;
	}
	
	/**
	 * Metodo que devuelve un ArrayList de tipo Accesorios con todos los que se encuentren en la BBDD
	 * @param con <- Conexion con la base de datos
	 * @return alAccesorios <- ArrayList que contiene todos los accesorios
	 */
	public static ArrayList<Accesorios> obtenerAccesorios(Connection con){
		ArrayList<Accesorios> alAccesorios = new ArrayList<>();
		Accesorios acs = null;
		String sent = "SELECT * FROM ACCESORIOS";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer precio = rs.getInt("PRECIO");
				String animalDirigido = rs.getString("ANIMAL_DIRIGIDO");
				String rutaFoto = rs.getString("rutaFoto");
				boolean enCesta = rs.getBoolean("EN_CESTA");  
				int unidades=rs.getInt("unidades");
				acs = new Accesorios(nombre, precio, animalDirigido, rutaFoto, enCesta, unidades); 
				alAccesorios.add(acs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alAccesorios;
	}
	
	/**
	 * Metodo que obtiene los gatos cuya localizacion es Beasain
	 * @param con -> Conexion con la BBDD
	 * @param loc -> localizacion
	 * @return
	 */
	public static ArrayList<Gatos> obtenerGatosBeasain(Connection con, String loc){
		ArrayList<Gatos> gatosBeasain = new ArrayList<>();
		Gatos g = null;
		String sent = "SELECT * FROM GATOS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				g = new Gatos(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				gatosBeasain.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return gatosBeasain;
	}
	/**
	 * Metodo que obtiene los gatos cuya localizacion es Vitoria
	 * @param con -> Conexion con la BBDD
	 * @param loc -> localizacion
	 * @return
	 */
	public static ArrayList<Gatos> obtenerGatosVitoria(Connection con, String loc){
		ArrayList<Gatos> gatosVitoria = new ArrayList<>();
		Gatos g = null;
		String sent = "SELECT * FROM GATOS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				g = new Gatos(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				gatosVitoria.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return gatosVitoria;
	}
	/**
	 * Metodo que obtiene los gatos cuya localizacion es Getxo
	 * @param con -> Conexion con la BBDD
	 * @param loc -> localizacion
	 * @return
	 */
	public static ArrayList<Gatos> obtenerGatosGetxo(Connection con, String loc){
		ArrayList<Gatos> gatosGetxo = new ArrayList<>();
		Gatos g = null;
		String sent = "SELECT * FROM GATOS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				g = new Gatos(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				gatosGetxo.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return gatosGetxo;
	}
	/**
	 * Metodo que obtiene los perros cuya localizacion es Beasain
	 * @param con -> Conexion con la BBDD
	 * @param loc -> localizacion
	 * @return
	 */
	public static ArrayList<Perros> obtenerPerrosBeasain(Connection con, String loc){
		ArrayList<Perros> perrosBeasain = new ArrayList<>();
		Perros p = null;
		String sent = "SELECT * FROM PERROS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				
				p = new Perros(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				perrosBeasain.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return perrosBeasain;
	}
	/**
	 * Método que obtiene los perros cuya localización es Vitoria
	 * @param con -> Conexión con la BBDD
	 * @param loc -> localización
	 * @return
	 */
	public static ArrayList<Perros> obtenerPerrosVitoria(Connection con, String loc){
		ArrayList<Perros> perrosVitoria = new ArrayList<>();
		Perros p = null;
		String sent = "SELECT * FROM PERROS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				
				p = new Perros(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				perrosVitoria.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return perrosVitoria;
	}
	/**
	 * Metodo que obtiene los perros cuya localización es Getxo
	 * @param con -> Conexion con la BBDD
	 * @param loc -> localizacion
	 * @return
	 */
	public static ArrayList<Perros> obtenerPerrosGetxo(Connection con, String loc){
		ArrayList<Perros> perrosGetxo = new ArrayList<>();
		Perros p = null;
		String sent = "SELECT * FROM PERROS WHERE localizacion = '"+loc+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				Boolean reservado = rs.getBoolean("RESERVADO");
				String rutaFoto = rs.getString("rutaFoto");
				
				p = new Perros(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, reservado, rutaFoto);
				perrosGetxo.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return perrosGetxo;
	}
	/***
	 * Metodo que borra los perros seleccionados
	 * @param nombre->El nombre del perro
	 */
	public static void borrarPerros(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "DELETE FROM PERROS WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que borra el gato cuyo nombre coincida con el que pasamos por parámetro
	 * @param con -> Conexion con la BBDD
	 * @param nombre -> El nombre del gato
	 */
	public static void borrarGatos(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "DELETE FROM GATOS WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que borra el animal otro cuyo nombre coincida con el que pasamos por parámetro
	 * @param con -> Conexion con la BBDD
	 * @param nombre -> El nombre del animal
	 */
	public static void borrarOtros(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "DELETE FROM OTROS WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que borra de la base de datos el accesorio que coincida con el que pasamos por parámetro
	 * @param con -> Conexion con la BBDD
	 * @param nombre -> El nombre del accesorio
	 */
	public static void borrarAccesorios(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "DELETE FROM ACCESORIOS WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que borra el alimento cuyo nombre coincida con el que pasamos por parámetro
	 * @param con -> Conexion con la BBDD
	 * @param nombre -> El nombre del alimento
	 */
	public static void borrarAlimentos(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "DELETE FROM ALIMENTOS WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que añade un nuevo perro a la BD pasandole por paramtero los siguientes valores
	 * @param con
	 * @param nombre
	 * @param edad
	 * @param sexo
	 * @param peso
	 * @param caracteristicas
	 * @param tiempoEnAdopcion
	 * @param localizacion
	 * @param colores
	 * @param reservado
	 * @param rutaFoto
	 */
	public static void anyadirPerro(Connection con, String nombre, int edad, String sexo, int peso, String caracteristicas,int tiempoEnAdopcion, String localizacion,String colores, boolean reservado,String rutaFoto) {
		try {
			Statement st = con.createStatement();
			String sent = "INSERT INTO PERROS VALUES ('"+nombre+"',"+edad+",'"+sexo+"',"+peso+",'"+caracteristicas+"',"+tiempoEnAdopcion+",'"+localizacion+"','"+colores+"','"+rutaFoto+"',"+reservado+")";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que añade un gato nuevo a la BBDD con los siguientes parámetros del mismo
	 * @param con
	 * @param nombre
	 * @param edad
	 * @param sexo
	 * @param peso
	 * @param caracteristicas
	 * @param tiempoEnAdopcion
	 * @param localizacion
	 * @param colores
	 * @param reservado
	 * @param rutaFoto
	 */
	public static void anyadirGato(Connection con, String nombre, int edad, String sexo, int peso, String caracteristicas,int tiempoEnAdopcion, String localizacion,String colores, boolean reservado,String rutaFoto) {
		try {
			Statement st = con.createStatement();
			String sent = "INSERT INTO GATOS VALUES ('"+nombre+"',"+edad+",'"+sexo+"',"+peso+",'"+caracteristicas+"',"+tiempoEnAdopcion+",'"+localizacion+"','"+colores+"','"+rutaFoto+"',"+reservado+")";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para añadir un nuevo animal "otro" a la base de datos pasandole los siguientes valores
	 * @param con
	 * @param nombre
	 * @param edad
	 * @param sexo
	 * @param peso
	 * @param caracteristicas
	 * @param peligroExtincion
	 * @param rutaFoto
	 */
	public static void anyadriOtro(Connection con, String nombre, int edad, String sexo, int peso, String caracteristicas, boolean peligroExtincion,String rutaFoto) {
		try {
			Statement st = con.createStatement();
			String sent = "INSERT INTO OTROS VALUES ('"+nombre+"',"+edad+",'"+sexo+"',"+peso+",'"+caracteristicas+"',"+peligroExtincion+",'"+rutaFoto+"')";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que añade un alimento nuevo a la BBDD pasandole los siguientes valores
	 * @param con
	 * @param nombre
	 * @param precio
	 * @param animal_dirigido
	 * @param rutaFoto
	 */
	public static void anyadirAlimento(Connection con, String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta, String fechaCaducidad) { 
		try {
			Statement st = con.createStatement();
			String sent = "INSERT INTO ALIMENTOS VALUES ('"+nombre+"',"+precio+",'"+animal_dirigido+"','"+rutaFoto+"',"+enCesta+",'"+fechaCaducidad+"')"; 
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que añade un nuevo accesorio a la base de datos teniendo en cuenta los siguientes valores, cuales pasamos por paramtero
	 * @param con
	 * @param nombre
	 * @param precio
	 * @param animal_dirigido
	 * @param rutaFoto
	 */
	public static void anyadirAccesorio(Connection con, String nombre, int precio, String animal_dirigido, String rutaFoto, boolean enCesta) {  
		try {
			Statement st = con.createStatement();
			String sent = "INSERT INTO ACCESORIOS VALUES ('"+nombre+"',"+precio+",'"+animal_dirigido+"','"+rutaFoto+"',"+enCesta+")"; 
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Método que cambia el valor en_cesta a 1 si el producto, en este caso el accesorio ha sido añadido a la cesta con el fin de comprarlo
	 * @param con <- Conexion con la base
	 * @param nombre <- El nombre del accesorio que ha sido reservado
	 */
	public static void accesorioReservado(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "UPDATE ACCESORIOS SET en_cesta = 1 WHERE nombre = '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que cambia el valor en_cesta a 1 si el alimento ha sido añadido a la cesta con el fin de comprarlo
	 * @param con <- Conexion con la base
	 * @param nombre <- El nombre del alimento que vaya a ser comprado
	 */
	public static void alimentoReservado(Connection con, String nombre) {
		try {
			Statement st = con.createStatement();
			String sent = "UPDATE ALIMENTOS SET en_cesta = 1 WHERE nombre = '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*** Método que cambia el valor de reservado a 0 en perros
	 * 
	 * @param con<- Conexion con la base
	 */
	public static void perrosACero(Connection con) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE PERROS SET reservado = 0";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*** Método que cambia el valor de reservado a 0 en gatos
	 * 
	 * @param con<- Conexion con la base
	 */
	public static void gatosACero(Connection con) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE GATOS SET reservado = 0";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*** Método  que cambia el valor de en_cesta a 0 en alimentos
	 * 
	 * @param con<- Conexion con la base
	 */
	public static void alimentosACero(Connection con) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE ALIMENTOS SET en_cesta = 0";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*** Método que cambia el valor de en_cesta a 0 en accesorios
	 * 
	 * @param con<- Conexion con la base
	 */
	public static void accesoriosACero(Connection con) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE ACCESORIOS SET en_cesta = 0";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*** Método que actualiza en la base de datos las unidades que quedan de alimentos
	 * 
	 * @param con<- Conexion con la base
	 * @param nombre<- El nombre del alimento
	 * @param unidadesUsuario<- unidades totales del producto menos las unidades que desea el usuario
	 */
	public static void alimentosUnidades(Connection con, String nombre, int unidadesUsuario) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE ALIMENTOS set unidades = "+unidadesUsuario+" WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param con<- Conexion con la base
	 * @param nombre<- El nombre del alimento
	 * @param unidadesUsuario<- unidades totales del producto menos las unidades que desea el usuario
	 */
	
	public static void accesoriosUnidades(Connection con, String nombre, int unidadesUsuario) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE ACCESORIOS set unidades = "+unidadesUsuario+" WHERE nombre= '"+nombre+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void actualizarUsuario(Connection con, String usuario) {
		try {
			Statement st=con.createStatement();
			String sent="UPDATE PERROS set usuario= '"+usuario+"'";
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
}


