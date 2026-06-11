package edu.ucam.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Asignatura;

public class DataBaseAsignatura {

	public static Map<Integer,Asignatura> listaAsignatura = new HashMap<Integer,Asignatura>();

	public static boolean remove(int id) {
		if(listaAsignatura.containsKey(id)) {
			listaAsignatura.remove(id);
			return true;
		}
		return false;
	}
	
	public static boolean modificar(Asignatura asignatura) {
	    listaAsignatura.put(asignatura.getId(), asignatura);
	    return true;
	}

	public static boolean alta(Asignatura asignatura) {
		asignatura.setId(siguienteId());
		listaAsignatura.put(asignatura.getId(), asignatura);
		return true;
	}

	public static Asignatura dameAsignaturaPorId(int id) {
		return listaAsignatura.get(id);
	}

	public static List<Asignatura> listar() {
		List<Asignatura> lista = new ArrayList<Asignatura>();
		for(Asignatura t: listaAsignatura.values()) {
			lista.add(t);
		}
		return lista;
	}
	
	private static int siguienteId() {
		int maximo = 0;
		for(Asignatura a: listaAsignatura.values()) {
			if(a.getId() > maximo)
				maximo = a.getId();
		}
		return ++maximo;
	}


}
