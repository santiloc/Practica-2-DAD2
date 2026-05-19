package edu.ucam.controller;

import jakarta.ws.rs.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import edu.ucam.utils.ParserObject;
import edu.ucam.beans.*;
import edu.ucam.database.DataBase;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/alumno")
public class AlumnoController {

	
	
	
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuestaAlumnos = new JSONObject();
		
		for(Alumno alu : DataBase.listaAlumnos) {
			respuestaAlumnos.append("alumnos", ParserObject.AlumnoToJSON(alu));
		}
		
		System.out.println("EN el metodo");
		return Response.status(200).entity(respuestaAlumnos.toString()).build();
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response borraAlumno(@PathParam("id") int idAlumno) {
		for(Alumno alu : DataBase.listaAlumnos) {
			if(alu.getId() == idAlumno) {
				DataBase.listaAlumnos.remove(alu);
				return Response.status(200).entity(true).build();
			}
		}
		
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

		if(jsonRecibido.has("id") ) {
			System.out.println("NO ES UNA ALTA, es una moidifcacion");
			return Response.status(409).entity(false).build();
		}

		Alumno alumno = ParserObject.JSONToAlumno(jsonRecibido);
		alumno.setId(siguienteId());

		DataBase.listaAlumnos.add(alumno);

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("alumno", ParserObject.AlumnoToJSON(alumno));
		
		return Response.status(200).entity(respuestaJSON.toString()).build();
	}
	
	private int siguienteId() {
		int maximo=0;
		
		for(Alumno alu: DataBase.listaAlumnos) {
			if(alu.getId() > maximo)
				maximo = alu.getId();
		}
		
		return ++maximo;
	}
	
	
	@GET
	@Path("/datos/{idAlumno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosAlumno(@PathParam("idAlumno") int id) {
		JSONObject responseJSON = new JSONObject();
		
		Alumno alumno = DataBase.dameAlumnoPorId(id);
		if(alumno != null) {
			JSONObject alumnoJson = ParserObject.AlumnoToJSON(alumno);
			responseJSON.put("alumno", alumnoJson);
			return Response.status(200).entity(responseJSON.toString()).build();
		}
		
		System.out.println("NO HAY ALUMNOS QUE CUMPLAN CON EL CRITERIO");
		return Response.status(404).entity(false).build();
	}
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificar(InputStream inputStream) {
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

		if(!jsonRecibido.has("id")) {
			System.out.println("NO ES UNA MODIFICACION, es una moidifcacion");
			return Response.status(409).entity(false).build();
		}

		Alumno alumno = ParserObject.JSONToAlumno(jsonRecibido);

		DataBase.alta(alumno);

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("alumno", ParserObject.AlumnoToJSON(alumno));
		
		return Response.status(200).entity(respuestaJSON.toString()).build();
		
	}
	
	
	
}
