package com.edu.uce.pw.api.repository;

import com.edu.uce.pw.api.repository.model.Materia;

public interface IMateriaRepository {

	void insertar(Materia materia);
	void actualizar(Materia materia);
	Materia seleccionar(Integer id);
	void eliminar(Integer id);

}