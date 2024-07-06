package com.edu.uce.pw.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.model.Materia;
import com.edu.uce.pw.api.service.IMateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriaController {

	@Autowired
	private IMateriaService materiaService;

	@PostMapping
	public void agregar(@RequestBody Materia materia) {
		this.materiaService.agregar(materia);
	}

	@PutMapping(path = "/{id}")
	public void modificar(@RequestBody Materia materia, @PathVariable Integer id) {
		materia.setId(id);
		this.materiaService.modificar(materia);
	}

	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.materiaService.borrar(id);
	}

	@GetMapping(path = "/{id}")
	public Materia buscar(@PathVariable Integer id) {
		return this.materiaService.buscar(id);
	}

	@GetMapping(path = "/credito")
	public List<Materia> buscarPorGenero(@RequestParam Integer credito) {
		List<Materia> lista = this.materiaService.buscarPorCredito(credito);
		return lista;
	}

	// Nivel 1: http://localhost:8082/API/v1.0/Matricula/materias/mixto/1
	@GetMapping(path = "/mixto/{id}")
	public Materia buscarMixto(@PathVariable Integer id) {

		// Aquí debes llamar al método que devuelve un Estudiante
		// Por ejemplo:
		return this.materiaService.buscar(id);
	}

	@PatchMapping(path = "/{id}")
	public void actualizarParcial(@RequestBody Materia m, @PathVariable Integer id) {
		m.setId(id);
		Materia e2 = this.materiaService.buscar(m.getId());
		if (m.getNombre() != null) {
			e2.setNombre(m.getNombre());
		}
		if (m.getCreditos() != null) {
			e2.setPrecio(m.getPrecio());
		}
		if (m.getProfesor() != null) {
			e2.setProfesor(m.getProfesor());
		}
		if (m.getSemestre() != null) {
			e2.setSemestre(m.getSemestre());
		}
		this.materiaService.modificar(e2);
	}
}
