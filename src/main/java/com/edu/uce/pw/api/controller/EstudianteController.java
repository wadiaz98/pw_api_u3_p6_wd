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

import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.IEstudianteService;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	// POST
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	@PostMapping
	public void guardar(@RequestBody Estudiante e) {
		this.estudianteService.guardar(e);
	}

	// PUT
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	@PutMapping
	public void actualizar(@RequestBody Estudiante e) {
		this.estudianteService.actualizar(e);
	}

	// PATCH
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	@PatchMapping
	public void actualizarParcial(@RequestBody Estudiante e) {
		Estudiante e2 = this.estudianteService.buscar(e.getId());
		if (e.getNombre() != null) {
			e2.setNombre(e.getNombre());
		}
		if (e.getApellido() != null) {
			e2.setApellido(e.getApellido());
		}
		if (e.getFecha() != null) {
			e2.setFecha(e.getFecha());
		}
		this.estudianteService.actualizar(e2);
	}

	// DELETE
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	@DeleteMapping(path = "/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}

	// GET
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1/nuevo/2
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/{id}/nuevo/{datro}
	@GetMapping(path = "/buscar/{id}/nuevo/{dato}")
	public Estudiante buscar(@PathVariable Integer id, @PathVariable String dato) {
		System.out.println("dato:" + dato);
		return this.estudianteService.buscar(id);
	}

	// GET
	// http://localhost:8082/API/v1.0/Matricula/estudiantes/genero?genero=F&edad=35
	@GetMapping(path = "/genero")
	public List<Estudiante> buscarPorGenero(@RequestParam String genero, @RequestParam Integer edad) {
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/buscarMixto/1?edad=15
	@GetMapping(path = "/buscarMixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam Integer edad) {
		System.out.println("Dato: " + id);
		System.out.println("Dato: " + edad);
		// Aquí debes llamar al método que devuelve un Estudiante
		// Por ejemplo:
		return this.estudianteService.buscar(id);
	}

}
