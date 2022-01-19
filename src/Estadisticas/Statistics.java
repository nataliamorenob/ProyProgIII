package Estadisticas;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BaseDeDatos.BD;
import Datos.Gatos;
import Datos.Otros;
import Datos.Perros;

public class Statistics {
	
	Connection con;
	
	
	public ArrayList<Integer> getPerrosSex() {
		con=BD.initBD("BaseDatos.db");
		ArrayList<Perros> LPerros = BD.obtenerPerros(con);
		ArrayList<Integer> perrosSexo=new ArrayList<>();
		int countM = 0;
		int countH = 0;
		for (Perros p : LPerros) {
			if (p.getSexo().equals("M")) {
				countM += 1;
				
			}else {
				countH +=1;
			}
			
			
		}
		perrosSexo.add(countM);
		perrosSexo.add(countH);
		return perrosSexo;
	}
	
	public ArrayList<Integer> getGatosSex() {
		con=BD.initBD("BaseDatos.db");
		ArrayList<Gatos> LGatos = BD.obtenerGatos(con);
		ArrayList<Integer> gatosSexo=new ArrayList<>();
		int countM = 0;
		int countH = 0;
		for (Gatos g : LGatos) {
			if (g.getSexo().equals("M")) {
				countM += 1;
			}else {
				countH +=1;
			}
			
		}
		gatosSexo.add(countM);
		gatosSexo.add(countH);
		return gatosSexo;
	}
	
	
	public ArrayList<Integer> getOtrosSex() {
		con=BD.initBD("BaseDatos.db");
		ArrayList<Otros> LOtros = BD.obtenerOtros(con);
		ArrayList<Integer> otrosSexo=new ArrayList<>();
		int countM = 0;
		int countH = 0;
		for (Otros o : LOtros) {
			if (o.getSexo().equals("M")) {
				countM += 1;
			}else {
				countH +=1;
			}
			
		}
		otrosSexo.add(countM);
		otrosSexo.add(countH);
		return otrosSexo;
	}
	

}
