package edu.ucam.services;

import java.util.List;
import edu.ucam.beans.Profesor;
import edu.ucam.database.DataBaseProfesor;
import edu.ucam.exception.NotFoundException;

public class ProfesorService {
    // Retorna la lista de todos los profesores
    public List<Profesor> listar() {
        return DataBaseProfesor.listar();
    }

    // Obtiene un profesor por su ID, manejando el error si no existe
    public Profesor obtenerPorId(int id) throws NotFoundException {
        Profesor profesor = DataBaseProfesor.dameProfesorPorId(id);
        if (profesor == null) {
            throw new NotFoundException("No existe el profesor con id " + id);
        }
        return profesor;
    }
    
    public boolean alta(Profesor profesor) {
        return edu.ucam.database.DataBaseProfesor.alta(profesor);
    }

    public boolean modificar(Profesor profesor) throws NotFoundException {
        if(edu.ucam.database.DataBaseProfesor.dameProfesorPorId(profesor.getId()) == null) {
            throw new NotFoundException("No existe el profesor para modificar");
        }
        return edu.ucam.database.DataBaseProfesor.modificar(profesor);
    }

    public boolean eliminar(int id) throws NotFoundException {
        if(edu.ucam.database.DataBaseProfesor.dameProfesorPorId(id) == null) {
            throw new NotFoundException("No existe el profesor para eliminar");
        }
        return edu.ucam.database.DataBaseProfesor.remove(id);
    }
}