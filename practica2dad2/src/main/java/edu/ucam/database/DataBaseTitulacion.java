package edu.ucam.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Titulacion;

import java.util.HashMap;

public class DataBaseTitulacion {

	public static Map<Integer,Titulacion> listaTitulacion = new HashMap<Integer,Titulacion>();

	public static boolean remove(int id) {
		if(listaTitulacion.containsKey(id)) {
			listaTitulacion.remove(id);
			return true;
		}
		return false;
	}

	public static boolean alta(Titulacion titulacion) {
		titulacion.setId(siguienteId());
		listaTitulacion.put(titulacion.getId(), titulacion);
		return true;
	}
	
	public static boolean modificar(Titulacion titulacion) {
		if(listaTitulacion.containsKey(titulacion.getId())) {
			listaTitulacion.put(titulacion.getId(), titulacion);
			return true;
		}
	
		return false;
	}

	public static Titulacion dameTitulacionPorId(int id) {
		return listaTitulacion.get(id);
	}

	public static List<Titulacion> listar() {
		List<Titulacion> lista = new ArrayList<Titulacion>();
		for(Titulacion t: listaTitulacion.values()) {
			lista.add(t);
		}
		return lista;
	}
	
	private static int siguienteId() {
		int maximo = 0;
		for(Titulacion t: listaTitulacion.values()) {
			if(t.getId() > maximo)
				maximo = t.getId();
		}
		return ++maximo;
	}
}
