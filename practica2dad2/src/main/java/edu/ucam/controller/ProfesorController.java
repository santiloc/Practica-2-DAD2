package edu.ucam.controller;

import org.json.JSONObject;
import edu.ucam.beans.Profesor;
import edu.ucam.services.ProfesorService;
import edu.ucam.utils.ParserObject;
import edu.ucam.exception.NotFoundException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profesor")
public class ProfesorController {
    
    private ProfesorService ps = new ProfesorService();

    // 1. OBTENER LISTADO (GET rest/profesor/listado)
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listado() {
        JSONObject respuestaProfesores = new JSONObject();
        for(Profesor p : ps.listar()) {
            respuestaProfesores.append("profesores", ParserObject.ProfesorToJSON(p));
        }
        return Response.status(200).entity(respuestaProfesores.toString()).build();
    }

    // 2. CREAR PROFESOR (POST rest/profesor)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        Profesor prof = ParserObject.JSONToProfesor(json);
        
        ps.alta(prof); 
        
        JSONObject respuesta = new JSONObject();
        respuesta.put("profesor", ParserObject.ProfesorToJSON(prof));
        return Response.status(201).entity(respuesta.toString()).build();
    }

    // 3. ACTUALIZAR PROFESOR (PUT rest/profesor)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(String jsonStr) {
        JSONObject json = new JSONObject(jsonStr);
        Profesor prof = ParserObject.JSONToProfesor(json);
        
        try {
            ps.modificar(prof); 
            JSONObject respuesta = new JSONObject();
            respuesta.put("profesor", ParserObject.ProfesorToJSON(prof));
            return Response.status(200).entity(respuesta.toString()).build();
        } catch (NotFoundException e) {
            return Response.status(404).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    // 4. BORRAR PROFESOR (DELETE rest/profesor/{id})
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") int id) {
        try {
            ps.eliminar(id); 
            return Response.status(200).entity(true).build();
        } catch (NotFoundException e) {
            return Response.status(404).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}