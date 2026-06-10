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
import edu.ucam.exception.ApiException;
import edu.ucam.services.AlumnoService;
import edu.ucam.services.TitulacionService;
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

	private AlumnoService as = new AlumnoService();

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuestaAlumnos = new JSONObject();
		/*
		for(Alumno a: as.listar()) {
			respuestaAlumno.append("alumnos", ParserObject.AlumnoToJSON(a));
		}
		return Response.status(200).entity(respuestaTitulaciones.toString()).build();
		*/
		
		return null;
	}


	@GET
	@Path("/datos/{idAlumno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosAlumno(@PathParam("idAlumno") int id) {
		
		/*
		try {
			Alumno alumno = as.obtenerPorId(id);
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("alumno", ParserObject.AlumnoToJSON(alumno));
			return Response.status(200).entity(responseJSON.toString()).build();
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}
		
		*/
		
		return null;
	}


	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borraAlumno(@PathParam("id") int idAlumno) {
		
		/*
		try {
			as.eliminar(idAlumno);
			return Response.status(200).entity(true).build();
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}
		
		*/
		
		return null;
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

		if(jsonRecibido.has("id")) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", "No se debe enviar id en el alta, se genera automaticamente");
			return Response.status(409).entity(errorJSON.toString()).build();
		}

		Alumno alumno = ParserObject.JSONToAlumno(jsonRecibido);

		/*
		try {
			as.alta(alumno);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("alumno", ParserObject.AlumnoToJSON(alumno));

		return Response.status(200).entity(respuestaJSON.toString()).build();
		*/
		
		return null;
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
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", "Se debe enviar el id para modificar un alumno");
			return Response.status(409).entity(errorJSON.toString()).build();
		}

		Alumno alumno = ParserObject.JSONToAlumno(jsonRecibido);

		/*
		try {
			as.modificar(alumno);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("alumno", ParserObject.AlumnoToJSON(alumno));

		return Response.status(200).entity(respuestaJSON.toString()).build();
		*/
		
		return null;
	}
	
	
}
