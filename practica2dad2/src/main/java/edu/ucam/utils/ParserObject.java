package edu.ucam.utils;

import org.json.JSONObject;

import edu.ucam.beans.Alumno;
import edu.ucam.beans.Titulacion;

public class ParserObject {

	public static Alumno JSONToAlumno(JSONObject alumnoJson) {
		Alumno alumno = new Alumno();
		
		if(alumnoJson.has("id"))
			alumno.setId(alumnoJson.getInt("id"));
		alumno.setNombre(alumnoJson.getString("nombre"));
		alumno.setApellido1(alumnoJson.getString("apellido1"));
			
		return alumno;
	}
	
	public static JSONObject AlumnoToJSON(Alumno alu) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", alu.getId());
		jsonObject.put("nombre", alu.getNombre());
		jsonObject.put("apellido1", alu.getApellido1());
	
		return jsonObject;
	}
	
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
}
