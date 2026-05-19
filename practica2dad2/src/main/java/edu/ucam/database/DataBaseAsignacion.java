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
            return asignaciones.get(idAsignatura).remove(Integer.valueOf(idProfesor));
        }
        return false;
    }

    public static void eliminarAsignacionesPorAsignatura(int idAsignatura) {
        asignaciones.remove(idAsignatura);
    }
}
