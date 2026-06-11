package edu.ucam.controller;

import java.util.List;
import org.json.JSONObject;
import edu.ucam.database.DataBaseAsignacion;
import edu.ucam.exception.ApiException;
import edu.ucam.exception.ConflictException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/asignacion")
public class AsignacionController {

    // Obtener todos los profesores asignados a una asignatura
    @GET
    @Path("/{idAsignatura}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAsignaciones(@PathParam("idAsignatura") int idAsignatura) {
        List<Integer> profesoresIds = DataBaseAsignacion.obtenerProfesoresDeAsignatura(idAsignatura);
        JSONObject responseJSON = new JSONObject();
        if (profesoresIds != null) {
            for (Integer idProfesor : profesoresIds) {
                responseJSON.append("profesoresIds", idProfesor);
            }
        }
        return Response.status(200).entity(responseJSON.toString()).build();
    }

    // Asignar un profesor a una asignatura
    @POST
    @Path("/{idAsignatura}/profesor/{idProfesor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response asignarProfesor(@PathParam("idAsignatura") int idAsignatura, @PathParam("idProfesor") int idProfesor) {
        boolean asignado = DataBaseAsignacion.asignarProfesor(idAsignatura, idProfesor);
        if (asignado) {
            return Response.status(200).entity(true).build();
        } else {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", "El profesor ya está asignado a esta asignatura");
            return Response.status(409).entity(errorJSON.toString()).build();
        }
    }

    // Eliminar la asignación de un profesor a una asignatura
    @DELETE
    @Path("/{idAsignatura}/profesor/{idProfesor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAsignacion(@PathParam("idAsignatura") int idAsignatura, @PathParam("idProfesor") int idProfesor) {
        boolean eliminado = DataBaseAsignacion.remove(idAsignatura, idProfesor);
        if (eliminado) {
            return Response.status(200).entity(true).build();
        } else {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("resultado", "La asignación no existe");
            return Response.status(404).entity(errorJSON.toString()).build();
        }
    }
}