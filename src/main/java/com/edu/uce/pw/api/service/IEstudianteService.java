package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.to.EstudianteTO;

public interface IEstudianteService {
	public Estudiante buscar(Integer id);

	public EstudianteTO buscarPorId(Integer id);

	public void actualizar(Estudiante estudiante);

	public void borrar(Integer id);

	public void registrar(Estudiante estudiante);

	List<Estudiante> seleccionarPorGenero(String genero);

	List<EstudianteTO> buscarTodos();

	// NUEVA Funcionalidad
	EstudianteTO buscarPorCedula(String cedula);

	void actualizarPorCedula(EstudianteTO estudianteTO);

	void eliminarPorCedula(String cedula);
	void agregar(EstudianteTO estudianteTO);

}