package com.example.finalPSP2324.model;

public class ProvinciaGetResponse {

	private Integer nProvincia;
	private String nombre;
	private Integer superficie;
	private Integer idCapital;

	public ProvinciaGetResponse() {}
	
	public ProvinciaGetResponse(Integer nProvincia, String nombre, Integer superficie, Integer idCapital) {
		super();
		this.nProvincia = nProvincia;
		this.nombre = nombre;
		this.superficie = superficie;
		this.idCapital = idCapital;
	}
	
	public Integer getnProvincia() {
		return nProvincia;
	}
	public void setnProvincia(Integer nProvincia) {
		this.nProvincia = nProvincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	public Integer getIdCapital() {
		return idCapital;
	}
	public void setIdCapital(Integer idCapital) {
		this.idCapital = idCapital;
	}
	
	@Override
	public String toString() {
		return "ProvinciaGetResponse [nProvincia=" + nProvincia + ", nombre=" + nombre + ", superficie=" + superficie
				+ ", idCapital=" + idCapital + "]";
	}
}
