package edu.ucam.beans;

public class Asignatura {

	private int id;
	private String nombre;
	private int creditos;
	
	//Constructor
	public Asignatura() {
		super();
	}
	
	//Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	
	
	
}
