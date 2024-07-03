package com.edu.uce.pw.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.model.Materia;
import com.edu.uce.pw.api.service.IMateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriaController {

	@Autowired
	private IMateriaService iMateriaService;

	// http://localhost:8080/API/v1.0/Matricula/materias/agregar
	@PostMapping(path = "/agregar")
	public void agregar(@RequestBody Materia materia) {
		this.iMateriaService.agregar(materia);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/modificar
	@PutMapping(path = "/modificar")
	public void modificar(@RequestBody Materia materia) {
		this.iMateriaService.modificar(materia);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/modificarParcial
	@PatchMapping(path = "/modificarParcial")
	public void modificarParcial(@RequestBody Materia materia) {
		Materia materia2 = this.iMateriaService.buscar(materia.getId());
		if (materia.getNombre() != null) {
			materia2.setNombre(materia.getNombre());
		}
		if (materia.getPrecio() != null) {
			materia2.setPrecio(materia.getPrecio());
		}
		if (materia.getProfesor() != null) {
			materia2.setProfesor(materia.getProfesor());
		}

		this.iMateriaService.modificar(materia2);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/borrar
	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.iMateriaService.borrar(id);

	}

	// http://localhost:8080/API/v1.0/Matricula/materias/encontrar
	@GetMapping(path = "/encontrar/{id}")
	public Materia encontrar(@PathVariable Integer id) {
		return this.iMateriaService.buscar(id);

	}

}
