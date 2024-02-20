package com.example.finalPSP2324.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LocalidadGetResponse {
	private Integer idLocalidad;
	private String nombre;
	private Integer poblacion;
	private Integer nProvincia;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProvinciaGetResponse provincia;

	public LocalidadGetResponse() {}
	
	public LocalidadGetResponse(Integer idLocalidad, String nombre, Integer poblacion, Integer nProvincia) {
		super();
		this.idLocalidad = idLocalidad;
		this.nombre = nombre;
		this.poblacion = poblacion;
		this.nProvincia = nProvincia;
	}
	
	public Integer getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
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
	
	
	
	public ProvinciaGetResponse getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaGetResponse provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "LocalidadGetResponse [idLocalidad=" + idLocalidad + ", nombre=" + nombre + ", poblacion=" + poblacion
				+ ", nProvincia=" + nProvincia + "]";
	}
}
