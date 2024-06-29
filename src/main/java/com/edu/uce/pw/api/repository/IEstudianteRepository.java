package com.edu.uce.pw.api.repository;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Estudiante;

public interface IEstudianteRepository {

	// CRUD
	public void insertar(Estudiante e);

	public Estudiante seleccionar(Integer id);

	public void actualizar(Estudiante e);

	public void eliminar(Integer id);
	
	public List<Estudiante> buscarPorGenero(String genero);
}
