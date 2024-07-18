package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.to.EstudianteTO;

public interface IEstudianteService {
	// CRUD
	public void guardar(Estudiante e);

	public Estudiante buscar(Integer id);

	public void actualizar(Estudiante e);

	public void borrar(Integer id);

	public List<Estudiante> buscarPorGenero(String genero);

	public EstudianteTO buscarPorId(Integer id);
}
