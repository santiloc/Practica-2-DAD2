package edu.ucam.utils;

import org.json.JSONObject;

import edu.ucam.beans.Asignatura;
import edu.ucam.beans.Profesor;
import edu.ucam.beans.Titulacion;

public class ParserObject {

	
	public static Titulacion JSONToTitulacion(JSONObject titulacionJson) {
		Titulacion titulacion = new Titulacion();

		if(titulacionJson.has("id"))
			titulacion.setId(titulacionJson.getInt("id"));
		titulacion.setNombre(titulacionJson.getString("nombre"));
		titulacion.setFacultad(titulacionJson.getString("facultad"));

		return titulacion;
	}

	public static JSONObject TitulacionToJSON(Titulacion titu) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", titu.getId());
		jsonObject.put("nombre", titu.getNombre());
		jsonObject.put("facultad", titu.getFacultad());
	
		return jsonObject;
	}
	
	
	public static Asignatura JSONToAsignatura(JSONObject asignaturaJson) {
		Asignatura asignatura = new Asignatura();
		if(asignaturaJson.has("id"))
			asignatura.setId(asignaturaJson.getInt("id"));
		asignatura.setNombre(asignaturaJson.getString("nombre"));
		asignatura.setCreditos(asignaturaJson.getInt("creditos"));
		return asignatura;
	}

	public static JSONObject AsignaturaToJSON(Asignatura asig) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", asig.getId());
		jsonObject.put("nombre", asig.getNombre());
		jsonObject.put("creditos", asig.getCreditos());
		return jsonObject;
	}
	
	public static Profesor JSONToProfesor(JSONObject profesorJson) {
		Profesor profesor = new Profesor();
		if(profesorJson.has("id"))
			profesor.setId(profesorJson.getInt("id"));
		profesor.setNombre(profesorJson.getString("nombre"));
		profesor.setApellido(profesorJson.getString("apellido"));
		return profesor;
	}

	public static JSONObject ProfesorToJSON(Profesor prof) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", prof.getId());
		jsonObject.put("nombre", prof.getNombre());
		jsonObject.put("apellido", prof.getApellido());
		return jsonObject;
	}
	
}
