package edu.ucam.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Titulacion;

import java.util.HashMap;

public class DataBaseTitulacion {

	public static Map<Integer,Titulacion> listaTitutulacion = new HashMap<Integer,Titulacion>();

	public static boolean remove(int id) {
		if(listaTitutulacion.containsKey(id)) {
			listaTitutulacion.remove(id);
			return true;
		}
		return false;
	}

	public static boolean alta(Titulacion titulacion) {
		titulacion.setId(siguienteId());
		listaTitutulacion.put(titulacion.getId(), titulacion);
		return true;
	}

	public static Titulacion dameTitulacionPorId(int id) {
		return listaTitutulacion.get(id);
	}

	public static List<Titulacion> listar() {
		List<Titulacion> lista = new ArrayList<Titulacion>();
		for(Titulacion t: listaTitutulacion.values()) {
			lista.add(t);
		}
		return lista;
	}
	
	private static int siguienteId() {
		int maximo = 0;
		for(Titulacion t: listaTitutulacion.values()) {
			if(t.getId() > maximo)
				maximo = t.getId();
		}
		return ++maximo;
	}
}
