package com.edu.uce.pw.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@PostMapping(path = "/guardar")
	public void guardar(@RequestBody Estudiante e) {
//		Estudiante e = new Estudiante();
//		e.setNombre("Willan");
//		e.setApellido("Diaz");
//		e.setFecha(LocalDateTime.of(1998, 6, 1, 10, 45, 20, 1));
		this.estudianteService.guardar(e);
	}

	// PUT
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	@PutMapping(path = "/actualizar")
	public void actualizar() {
		Estudiante e = this.estudianteService.buscar(1);
		e.setNombre("Alexander");
		e.setApellido("Cordova");
		e.setFecha(LocalDateTime.of(1998, 6, 5, 10, 45, 20, 1));
		this.estudianteService.actualizar(e);
	}

	// PATCH
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	@PatchMapping(path = "/actualizarParcial")
	public void actualizarParcial() {
		Estudiante e = this.estudianteService.buscar(1);
		e.setNombre("Alexander");
		this.estudianteService.actualizar(e);
	}

	// DELETE
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	@DeleteMapping(path = "/borrar")
	public void borrar() {
		this.estudianteService.borrar(1);
	}

	// GET
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	@GetMapping(path = "/buscar")
	public Estudiante buscar() {
		return this.estudianteService.buscar(2);
	}
}
