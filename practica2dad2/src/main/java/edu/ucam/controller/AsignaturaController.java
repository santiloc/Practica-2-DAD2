package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.ucam.beans.Asignatura;
import edu.ucam.exception.ApiException;
import edu.ucam.services.AsignaturaService;
import edu.ucam.utils.ParserObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/asignatura")
public class AsignaturaController {
	private AsignaturaService as = new AsignaturaService();

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuestaAsignatura = new JSONObject();
		for(Asignatura a: as.listar()) {
			respuestaAsignatura.append("asignatura", ParserObject.AsignaturaToJSON(a));
		}
		return Response.status(200).entity(respuestaAsignatura.toString()).build();
	}


	@GET
	@Path("/datos/{idAsignatura}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosAsignatura(@PathParam("idAsignatura") int id) {
		try {
			Asignatura titulacion = as.obtenerPorId(id);
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("Asignatura", ParserObject.AsignaturaToJSON(titulacion));
			return Response.status(200).entity(responseJSON.toString()).build();
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}
	}


	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borraAsignatura(@PathParam("id") int idAsignatura) {
		try {
			as.eliminar(idAsignatura);
			return Response.status(200).entity(true).build();
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}
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

		Asignatura asignatura = ParserObject.JSONToAsignatura(jsonRecibido);

		try {
			as.alta(asignatura);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("asignatura", ParserObject.AsignaturaToJSON(asignatura));

		return Response.status(200).entity(respuestaJSON.toString()).build();
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
			errorJSON.put("resultado", "Se debe enviar el id para modificar una asignatura");
			return Response.status(409).entity(errorJSON.toString()).build();
		}

		Asignatura asignatura = ParserObject.JSONToAsignatura(jsonRecibido);

		try {
			as.modificar(asignatura);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("asignatura", ParserObject.AsignaturaToJSON(asignatura));

		return Response.status(200).entity(respuestaJSON.toString()).build();
	}
}
