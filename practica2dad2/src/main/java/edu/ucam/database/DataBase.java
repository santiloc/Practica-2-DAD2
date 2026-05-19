package edu.ucam.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.*;


public class DataBase {
	public static Map<Integer,Alumno> listaAlumnos = new HashMap<Integer,Alumno>();

	
	public static boolean remove(int id) {
		if(listaAlumnos.containsKey(id)) {
			listaAlumnos.remove(id);
			return true;
		}
		return false;
	}

	public static boolean alta(Alumno alumno) {
		alumno.setId(siguienteId());
		listaAlumnos.put(alumno.getId(), alumno);
		return true;
	}

	public static Alumno dameAlumnnoPorId(int id) {
		return listaAlumnos.get(id);
	}

	public static List<Alumno> listar() {
		List<Alumno> lista = new ArrayList<Alumno>();
		for(Alumno a: listaAlumnos.values()) {
			lista.add(a);
		}
		return lista;
	}
	
	private static int siguienteId() {
		int maximo = 0;
		for(Alumno a: listaAlumnos.values()) {
			if(a.getId() > maximo)
				maximo = a.getId();
		}
		return ++maximo;
	}
	
}
