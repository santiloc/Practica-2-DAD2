package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.ucam.beans.Titulacion;
import edu.ucam.exception.ApiException;
import edu.ucam.exception.ConflictException;
import edu.ucam.services.TitulacionService;
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

@Path("/titulacion")
public class TitulacionController {

	private TitulacionService ts = new TitulacionService();

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuestaTitulaciones = new JSONObject();
		for(Titulacion t: ts.listar()) {
			respuestaTitulaciones.append("titulaciones", ParserObject.TitulacionToJSON(t));
		}
		return Response.status(200).entity(respuestaTitulaciones.toString()).build();
	}


	@GET
	@Path("/datos/{idTitulacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response datosTitulacion(@PathParam("idTitulacion") int id) {
		try {
			Titulacion titulacion = ts.obtenerPorId(id);
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("titulacion", ParserObject.TitulacionToJSON(titulacion));
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
	public Response borraTitulacion(@PathParam("id") int idTitulacion) {
		try {
			ts.eliminar(idTitulacion);
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

		Titulacion titulacion = ParserObject.JSONToTitulacion(jsonRecibido);

		try {
			ts.alta(titulacion);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("titulacion", ParserObject.TitulacionToJSON(titulacion));

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
			errorJSON.put("resultado", "Se debe enviar el id para modificar una titulacion");
			return Response.status(409).entity(errorJSON.toString()).build();
		}

		Titulacion titulacion = ParserObject.JSONToTitulacion(jsonRecibido);

		try {
			ts.modificar(titulacion);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("titulacion", ParserObject.TitulacionToJSON(titulacion));

		return Response.status(200).entity(respuestaJSON.toString()).build();
	}
}
