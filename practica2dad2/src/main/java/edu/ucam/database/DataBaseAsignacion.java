package edu.ucam.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseAsignacion {
	public static Map<Integer, List<Integer>> asignaciones = new HashMap<Integer, List<Integer>>();

    public static boolean asignarProfesor(int idAsignatura, int idProfesor) {
        asignaciones.putIfAbsent(idAsignatura, new ArrayList<Integer>());
        List<Integer> profesores = asignaciones.get(idAsignatura);
        
        if(!profesores.contains(idProfesor)) {
            profesores.add(idProfesor);
            return true;
        }
        return false;
    }

    public static List<Integer> obtenerProfesoresDeAsignatura(int idAsignatura) {
        return asignaciones.get(idAsignatura);
    }

    public static boolean remove(int idAsignatura, int idProfesor) {
        if(asignaciones.containsKey(idAsignatura)) {
        	List<Integer> profesores = asignaciones.get(idAsignatura);
        	if(profesores.contains(idProfesor)) {
        		return asignaciones.get(idAsignatura).remove(Integer.valueOf(idProfesor));
        	}
        }
        
        return false;
    }

    public static boolean eliminarAsignacionesPorAsignatura(int idAsignatura) {
       if(asignaciones.containsKey(idAsignatura) ) {
    	   asignaciones.remove(idAsignatura);
    	   return true;
       }
       
       return false;
    }
    
    
}
