package edu.ucam.database;

import java.util.ArrayList;
import java.util.List;

import edu.ucam.beans.*;


public class DataBase {
	public static List<Alumno> listaAlumnos = new ArrayList<Alumno>();
	
	
	public static boolean remove(int id) {
		boolean result = false;
		Alumno alumno = dameAlumnoPorId(id);
		listaAlumnos.remove(alumno);
		return result;
	}
	
	
	public static boolean alta(Alumno alumno) {
		boolean resultado = false;
		
		if(dameAlumnoPorId(alumno.getId()) == null) {
			listaAlumnos.add(alumno);
			resultado = true;
		} else {
			if(remove(alumno.getId())) {
				listaAlumnos.add(alumno);
				resultado = true;
			}
			
		}
		
		return resultado;
	} 
	
	
	
	
	public static Alumno dameAlumnoPorId(int id) {
		for(Alumno alu: listaAlumnos) {
			if(alu.getId() == id)
				return alu;
		}
		
		return null;
	}
	
}
