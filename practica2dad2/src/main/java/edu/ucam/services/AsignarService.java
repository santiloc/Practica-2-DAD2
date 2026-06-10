package edu.ucam.services;

import edu.ucam.database.DataBaseAsignacion;
import edu.ucam.exception.NotFoundException;

public class AsignarService {
	public boolean asignarAsignatura(int idProfe, int idAsig) throws NotFoundException {
		ProfesorService ps = new ProfesorService();
		AsignaturaService as = new AsignaturaService();
		
	    ps.obtenerPorId(idProfe);
	    as.obtenerPorId(idAsig);
	    
	    return DataBaseAsignacion.asignarProfesor(idAsig, idProfe);
	}
	
	public boolean eliminarProfesorAsignado(int idAsignatura, int idProfesor) throws NotFoundException {
		boolean eliminado = DataBaseAsignacion.remove(idAsignatura, idProfesor);
		
		if(!eliminado) {
			throw new NotFoundException(
		            "No existe la asignación entre la asignatura " 
		            + idAsignatura + " y el profesor " + idProfesor
		        );
		}
		
		return true;
	}
	
	public boolean eliminarAsignacion(int idAsignatura) throws NotFoundException {
		boolean eliminado = DataBaseAsignacion.eliminarAsignacionesPorAsignatura(idAsignatura);

	    if(!eliminado) {
	        throw new NotFoundException(
	            "No existen asignaciones para la asignatura " + idAsignatura
	        );
	    }

	    return true;
	}
}
