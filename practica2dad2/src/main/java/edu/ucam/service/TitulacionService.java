package edu.ucam.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.ucam.beans.Alumno;
import edu.ucam.database.DataBase;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/titulacion")
public class TitulacionService {


	
	@GET
	@Path("/listado/{idTitulacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuestaAlumnos = new JSONObject();
		
		
		
		return Response.status(200).entity(respuestaAlumnos.toString()).build();
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response borraAlumno(@PathParam("id") int idAlumno) {
		
		
		return Response.status(401).entity(false).build();
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alta(InputStream inputStream) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String linea = "";
		try {
			while((linea = bReader.readLine()) != null) {
				sb.append(linea);
			}
		} catch (IOException e) {
			return Response.status(500).entity(true).build();
		}
		
		
		JSONObject jsonRecibido = new JSONObject(sb.toString());
		Alumno alumno = new Alumno();

		if(jsonRecibido.has("id") && !(jsonRecibido.getString("id").isEmpty())) {
			System.out.println("NO ES UNA ALTA, es una moidifcacion");
			return Response.status(409).entity(false).build();
		} else {
			alumno.setId(siguienteId());
			alumno.setNombre(jsonRecibido.getString("nombre"));
			alumno.setApellido1(jsonRecibido.getString("apellido1"));
		}
		
		DataBase.listaAlumnos.add(alumno);
		
		JSONObject respuestaJSON = new JSONObject();
		JSONObject alumnoJSON = new JSONObject();
		alumnoJSON.put("id", alumno.getId());
		alumnoJSON.put("nombre", alumno.getNombre());
		alumnoJSON.put("apellido1", alumno.getApellido1());
		respuestaJSON.put("alumno", alumnoJSON);
		
		return Response.status(200).entity(respuestaJSON.toString()).build();
	}
	
	
}
