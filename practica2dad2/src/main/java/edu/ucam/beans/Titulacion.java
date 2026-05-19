package edu.ucam.beans;

public class Titulacion {

	private String nombre;
	private int id;
	private String facultad;
	
	
	public Titulacion() {
		super();
	}
	public Titulacion(String nombre, int id, String facultad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.facultad = facultad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	
	
	
}
