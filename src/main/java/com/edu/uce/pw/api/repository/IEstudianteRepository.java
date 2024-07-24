package com.edu.uce.pw.api.repository;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Estudiante;

public interface IEstudianteRepository {

	// CRUD
	Estudiante seleccionar(Integer id);

	void actualizar(Estudiante estudiante);

	void eliminar(Integer id);

	void insertar(Estudiante estudiante);

	List<Estudiante> seleccionarPorGenero(String genero);

	List<Estudiante> seleccionarTodos();

	//NUEVA FUNCIONALIDAD
	Estudiante seleccionarPorCedula(String cedula);
	void eliminarPorCedula(String cedula);
	
	

}	