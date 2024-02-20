package com.example.finalPSP2324.model;

public class LocalidadPostRequest {

	private String nombre;
	private Integer poblacion;
	private Integer nProvincia;
	

	public LocalidadPostRequest() {}
	
	public LocalidadPostRequest(String nombre, Integer poblacion, Integer nProvincia) {
		super();
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.nProvincia = nProvincia;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	public Integer getnProvincia() {
		return nProvincia;
	}
	public void setnProvincia(Integer nProvincia) {
		this.nProvincia = nProvincia;
	}
	
	@Override
	public String toString() {
		return "LocalidadPostRequest [nombre=" + nombre + ", poblacion=" + poblacion + ", nProvincia=" + nProvincia + "]";
	}
}
