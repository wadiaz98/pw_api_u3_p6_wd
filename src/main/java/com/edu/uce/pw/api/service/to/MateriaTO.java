package com.edu.uce.pw.api.service.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class MateriaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -877524163119193403L;

	// No se debe poner los relacionamientos Uno a muchos etc.
	private Integer id;

	private String nombre;

	private Integer creditos;

	private BigDecimal precio;

	private String semestre;

	private String profesor;

	// SET y GET

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}