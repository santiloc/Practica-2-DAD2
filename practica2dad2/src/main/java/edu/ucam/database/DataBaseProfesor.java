package edu.ucam.database;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Profesor

;
public class DataBaseProfesor {

	public static Map<Integer, Profesor> listaProfesores = new HashMap<>();
	
	public static void inicializarProfesores() {
		if(listaProfesores.isEmpty()) {
			listaProfesores.put(1, new Profesor(1, "Miguel Ángel", "Guillén Navarro"));
			listaProfesores.put(2, new Profesor(2, "Alejandor", "Sánchez Rodríguez"));
		}
	}

    public static Profesor dameProfesorPorId(int id) {
        return listaProfesores.get(id);
    }
    
    public static List<Profesor> listar() {
        return new ArrayList<>(listaProfesores.values());
    }
}
