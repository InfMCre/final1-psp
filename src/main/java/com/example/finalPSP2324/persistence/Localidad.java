package com.example.finalPSP2324.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Localidades")
public class Localidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLocalidad;
	@Column
	private String nombre;
	@Column
	private Integer poblacion;
	
	@Column(name="n_provincia", updatable=false, insertable=false)
	private Integer nProvincia;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "n_provincia")
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Provincia provincia;
	

	@OneToOne(mappedBy = "capital", fetch = FetchType.LAZY)
	@JsonBackReference
	private Provincia capitalDeProvincia;
	
	
	public Localidad() {}
	
	public Localidad(Integer idLocalidad, String nombre, Integer poblacion, Integer nProvincia) {
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
	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Provincia getCapitalDeProvincia() {
		return capitalDeProvincia;
	}

	public void setCapitalDeProvincia(Provincia capitalDeProvincia) {
		this.capitalDeProvincia = capitalDeProvincia;
	}

	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", nombre=" + nombre + ", poblacion=" + poblacion
				+ ", nProvincia=" + nProvincia + "]";
	}
}
