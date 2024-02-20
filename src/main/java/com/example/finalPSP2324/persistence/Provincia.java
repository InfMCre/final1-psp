package com.example.finalPSP2324.persistence;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Provincias")
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nProvincia;
	@Column
	private String nombre;
	@Column
	private Integer superficie;
	@Column(name="id_capital", updatable=false, insertable=false)
	private Integer idCapital;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_capital")
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

	private Localidad capital;
	

	@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Localidad> localidades;
	
	public Provincia() {}
	
	public Provincia(Integer nProvincia, String nombre, Integer superficie, Integer idCapital) {
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
	
	
	
	public Localidad getCapital() {
		return capital;
	}

	public void setCapital(Localidad capital) {
		this.capital = capital;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

	@Override
	public String toString() {
		return "Provincia [nProvincia=" + nProvincia + ", nombre=" + nombre + ", superficie=" + superficie
				+ ", idCapital=" + idCapital + "]";
	}
}
