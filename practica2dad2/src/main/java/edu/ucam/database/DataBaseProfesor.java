package edu.ucam.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.ucam.beans.Profesor;

public class DataBaseProfesor {
	public static Map<Integer, Profesor> listaProfesores = new HashMap<>();

	public static Profesor dameProfesorPorId(int id) {
		return listaProfesores.get(id);
	}
	public static List<Profesor> listar() {
		return new ArrayList<>(listaProfesores.values());
	}
	public static boolean alta(Profesor profesor) {
		profesor.setId(siguienteId());
		listaProfesores.put(profesor.getId(), profesor);
		return true;
	}
	public static boolean modificar(Profesor profesor) {
		listaProfesores.put(profesor.getId(), profesor);
		return true;
	}
	public static boolean remove(int id) {
		if(listaProfesores.containsKey(id)) {
			listaProfesores.remove(id);
			return true;
		}
		return false;
	}
	private static int siguienteId() {
		int maximo = 0;
		for(Profesor p: listaProfesores.values()) {
			if(p.getId() > maximo) maximo = p.getId();
		}
		return ++maximo;
	}
}