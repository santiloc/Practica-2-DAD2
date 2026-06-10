package edu.ucam.controller;

import org.json.JSONObject;

import edu.ucam.beans.Profesor;
import edu.ucam.services.ProfesorService;
import edu.ucam.utils.ParserObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profesor")
public class ProfesorController {
    private ProfesorService ps = new ProfesorService();

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        JSONObject listaProfesores = new JSONObject();

        for(Profesor p : ps.listar()) {
            listaProfesores.append("profesores", ParserObject.ProfesorToJSON(p));
        }

        return Response.status(200).entity(listaProfesores.toString()).build();
    }
}
