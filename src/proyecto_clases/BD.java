package proyecto_clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BD {
	private static Connection con;
	
	//Método que establece la conexión con la BBDD
	
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
	
	//Método que cierra la conexión con la BBDD
	
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
		String sent3 = "CREATE TABLE IF NOT EXISTS Otros(estimacionAnyo Integer, peligroExtincion boolean)";
		String sent4 = "CREATE TABLE IF NOT EXISTS Alimentos(nombre String, precio Integer, animal_dirigido String)";
		String sent5 = "CREATE TABLE IF NOT EXISTS Accesorios(nombre String, precio Integer, animal_dirigido String)";
		//String sent6 = "CREATE TABLE IF NOT EXISTS Usuario(usuario String, contrasenia String)";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
			st.executeUpdate(sent3);
			st.executeUpdate(sent4);
			st.executeUpdate(sent5);
		//	st.executeUpdate(sent6);
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
	
	//xq es int? X LO QUE DEVUELVE--->0 SI NO EXISTE, 1 SI EXISTE PERO CONTRA NO CORRECTO, 2 SI CONTRA Y USUARIO CORRECTOS
	public static int cogerUsuario(String usuario, String contrasenia){
		String resolucion="SELECT password FROM LogIn WHERE usuario_ ='"+usuario+"'";
		java.sql.Statement stat=null;
		int resultado=0;
		try {
			stat=con.createStatement();
			ResultSet resuls= stat.executeQuery(resolucion);
			if(resuls.next()) {
				if(resuls.getString("password").equals(contrasenia)) {
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
	public static void anyadirUsuario(String usuario, String contrasenia ) {
		String resolucion="INSERT INTO LogIn VALUES('"+usuario+"','"+contrasenia+"')";
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
	
	
	
	
	public static ArrayList<Perros> obtenerPerros(Connection con) {
		ArrayList<Perros> ALPerros=new ArrayList<>();
		Perros p=null;
		String sent = "SELECT * FROM T_PERROS";
		Statement st = null;
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) { 
				String nombre = rs.getString("NOMBRE");
				Integer edad = rs.getInt("EDAD");
				String sexo = rs.getString("SEXO");
				Integer peso =rs.getInt("PESO");
				String caracteristicas = rs.getString("CARACTERISTICAS");
				Integer tiempoEnAdopcion = rs.getInt("TIEMPOENADOPCION");
				String localizacion = rs.getString("LOCALIZACION");
				String colores = rs.getString("COLORES");
				String rutaFoto = rs.getString("FOTO");
				p = new Perros(nombre,edad,sexo,peso,caracteristicas,tiempoEnAdopcion,localizacion,colores,rutaFoto);
				ALPerros.add(p);
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
		
		return ALPerros;
	}
}





