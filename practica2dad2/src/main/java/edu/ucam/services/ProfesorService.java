package edu.ucam.services;

import java.util.List;

import edu.ucam.beans.Asignatura;
import edu.ucam.beans.Profesor;
import edu.ucam.database.DataBaseAsignatura;
import edu.ucam.database.DataBaseProfesor;
import edu.ucam.exception.NotFoundException;

public class ProfesorService {
	public List<Profesor> listar() {
		return DataBaseProfesor.listar();
	}
	
	public Profesor obtenerPorId(int id) throws NotFoundException { 
		Profesor p = DataBaseProfesor.dameProfesorPorId(id);
		if(p == null) {
			throw new NotFoundException("No existe el profesor con id " + id);
		}
		return p;
	}
	
	public void inicializar() {
		DataBaseProfesor.inicializarProfesores();
	}
}
