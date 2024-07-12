package com.edu.uce.pw.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

	@PostMapping(produces = "application/xml", consumes = "application/json")
	public ResponseEntity<Materia> agregar(@RequestBody Materia materia) {
		this.materiaService.agregar(materia);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_201", "Materia agregada correctamente");

		return ResponseEntity.status(201).headers(cabeceras).body(materia);
	}

	@PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Materia> modificar(@RequestBody Materia materia, @PathVariable Integer id) {
		materia.setId(id);
		this.materiaService.modificar(materia);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_238", "Materia modificada correctamente");

		return ResponseEntity.status(238).headers(cabeceras).body(materia);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.materiaService.borrar(id);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_240", "Materia borrada exitosamente");

		return ResponseEntity.status(240).headers(cabeceras).body("Borrada exitosamente");
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Materia> buscar(@PathVariable Integer id) {
		Materia materia = this.materiaService.buscar(id);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");

		return ResponseEntity.status(236).headers(cabeceras).body(materia);
	}

	@GetMapping(path = "/credito", produces = "application/json")
	public List<Materia> buscarPorCredito(@RequestParam Integer credito) {
		List<Materia> lista = this.materiaService.buscarPorCredito(credito);
		return lista;
	}

	@GetMapping(path = "/mixto/{id}", produces = "application/json")
	public Materia buscarMixto(@PathVariable Integer id) {
		return this.materiaService.buscar(id);
	}

	@PatchMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Materia> actualizarParcial(@RequestBody Materia m, @PathVariable Integer id) {
		m.setId(id);
		Materia materia = this.materiaService.buscar(m.getId());
		if (m.getNombre() != null) {
			materia.setNombre(m.getNombre());
		}
		if (m.getCreditos() != null) {
			materia.setCreditos(m.getCreditos());
		}
		if (m.getProfesor() != null) {
			materia.setProfesor(m.getProfesor());
		}
		if (m.getSemestre() != null) {
			materia.setSemestre(m.getSemestre());
		}
		this.materiaService.modificar(materia);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_239", "Materia actualizada parcialmente");

		return ResponseEntity.status(239).headers(cabeceras).body(materia);
	}
}
