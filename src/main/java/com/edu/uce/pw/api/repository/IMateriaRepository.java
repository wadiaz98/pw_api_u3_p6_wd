package com.edu.uce.pw.api.repository;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Materia;

public interface IMateriaRepository {

	void insertar(Materia materia);
	void actualizar(Materia materia);
	Materia seleccionar(Integer id);
	void eliminar(Integer id);
	List<Materia> buscarPorCredito(Integer credito);
	public List<Materia> seleccionarPorIdEstudiante(Integer id);
}