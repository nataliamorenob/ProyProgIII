package proyecto_clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BD {
	private static Connection con;
	

	//M�todo que establece la conexi�n con la BBDD

	/**
	 * M�todo que establece la conexi�n con la BBDD
	 * @param nombreBD <- El nombre de la BBDD
	 * @return con <- La conexi�n con la BBDD
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
	

	//M�todo que cierra la conexi�n con la BBDD

	/**
	 * M�todo que cierra la conexi�n con la BBDD
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
 		String sent1 = "CREATE TABLE IF NOT EXISTS Perros(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, tiempoEnAdopcion Integer, localizacion String, colores String,rutaFoto String)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Gatos(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, tiempoEnAdopcion Integer, localizacion String, colores String,rutaFoto String)";
		String sent3 = "CREATE TABLE IF NOT EXISTS Otros(nombre String, edad Integer, sexo String, peso Integer, caracteristicas String, peligroExtincion boolean, rutaFoto String)";
		String sent4 = "CREATE TABLE IF NOT EXISTS Alimentos(nombre String, precio Integer, animal_dirigido String, rutaFoto String)";
		String sent5 = "CREATE TABLE IF NOT EXISTS Accesorios(nombre String, precio Integer, animal_dirigido String, rutaFoto String)";
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
	 * M�todo que devuelve un valor entero dependiendo de si el usuario existe con la contrase�a pasada por par�metro
	 * @param usuario <- el nick del usuario con el que se registrar�
	 * @param contrasenia <- la contrase�a con la que el usuario se registra
	 * @return 
	 * -  0 si no exite dicho usuario
	 * -  1 si existe el usuario pero la contrase�a no es correcta
	 * -  2 si el usuario existe y la contrase�a es correcta
	 */
	public static int cogerUsuario(String usuario, String contrasenia){
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
	 * M�todo para a�adir un nuevo usuario a la BBDD
	 * @param usuario  <- el nick del usuario con el que se registrar� 
	 * @param contrasenia <- la contrase�a con la que el usuario se registra
	 */
	public static void anyadirUsuario(String usuario, String contrasenia ) {
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
	 * M�todo que devuelve un ArrayList de todos los perros que hay en la BBDD
	 * @param con <- La conexi�n con la BBDD
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
				String rutaFoto = rs.getString("rutaFoto");
				p = new Perros(nombre,edad,sexo,peso,caracteristicas,tiempoEnAdopcion,localizacion,colores,rutaFoto);
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
	 * M�todo que devuelve un ArrayList de todos los gatos que hay en la BBDD
	 * @param con <- La conexi�n con la BBDD
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
				String rutaFoto = rs.getString("rutaFoto");
				g = new Gatos(nombre, edad, sexo, peso, caracteristicas, tiempoEnAdopcion, localizacion, colores, rutaFoto);
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
	 * M�todo que devuelve un ArrayList de todos los animales no adoptables (otros) que hay en la BBDD
	 * @param con <- La conexi�n con la BBDD
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
	 * M�todo que devuelve un ArrayList de tipo Alimentos con todos los alimentos que se encuentren en la BBDD
	 * @param con <- Conexi�n con la base de datos
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
				amt = new Alimentos(nombre, precio, animalDirigido, rutaFoto);
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
	 * M�todo que devuelve un ArrayList de tipo Accesorios con todos los que se encuentren en la BBDD
	 * @param con <- Conexi�n con la base de datos
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
				acs = new Accesorios(nombre, precio, animalDirigido, rutaFoto);
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
}





