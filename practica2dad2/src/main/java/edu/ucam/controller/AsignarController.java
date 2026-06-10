package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.ucam.exception.ApiException;
import edu.ucam.services.AsignarService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/asignacion")
public class AsignarController {
    private AsignarService as = new AsignarService();

    @PUT
    @Path("/asignar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response asignarProfesor(InputStream inputStream) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }

            JSONObject jsonRecibido = new JSONObject(sb.toString());

            int idProfesor = jsonRecibido.getInt("idProfesor");
            int idAsignatura = jsonRecibido.getInt("idAsignatura");

            as.asignarAsignatura(idProfesor, idAsignatura);

            JSONObject respuesta = new JSONObject();
            respuesta.put("resultado", "Profesor asignado correctamente");
            respuesta.put("idProfesor", idProfesor);
            respuesta.put("idAsignatura", idAsignatura);

            return Response.status(200).entity(respuesta.toString()).build();

        } catch (ApiException e) {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", e.getMessage());
            return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();

        } catch (Exception e) {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", "Error al asignar profesor");
            return Response.status(500).entity(errorJSON.toString()).build();
        }
    }
    
    @DELETE
    @Path("/{idAsig}/profesor/{idProfe}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProfesorAsignado(@PathParam("idAsig") int idAsig, @PathParam("idProfe") int idProfe) {
    	try {
    		as.eliminarProfesorAsignado(idAsig, idProfe);
            return Response.status(200).entity(true).build();
    	} catch (ApiException e) {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", e.getMessage());
            return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();

        } catch (Exception e) {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", "Error al asignar profesor asignado");
            return Response.status(500).entity(errorJSON.toString()).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAsignacion(@PathParam("id") int idAsignatura) {
    	try {
    		as.eliminarAsignacion(idAsignatura);
    		return Response.status(200).entity(true).build();
    	} catch(ApiException e) {
    		JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", e.getMessage());
            return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
    	} catch (Exception e) {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", "Error al eliminar asignacion");
            return Response.status(500).entity(errorJSON.toString()).build();
        }
    }
}
