package com.edu.uce.pw.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.IEstudianteService;
import com.edu.uce.pw.api.service.IMateriaService;
import com.edu.uce.pw.api.service.to.EstudianteTO;
import com.edu.uce.pw.api.service.to.MateriaTO;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {
	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService iMateriaService;

	// POST
	@PostMapping(produces = "application/xml", consumes = "application/json")
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante e) {
		this.estudianteService.guardar(e);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_201", "Estudiante guardado correctamente");

		return ResponseEntity.status(HttpStatus.CREATED).headers(cabeceras).body(e);
	}

	// PUT
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante e, @PathVariable Integer id) {
		e.setId(id);
		this.estudianteService.actualizar(e);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_238", "Estudiante actualizado correctamente");

		return ResponseEntity.status(238).headers(cabeceras).body(e);
	}

	// PATCH
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> actualizarParcial(@RequestBody Estudiante e, @PathVariable Integer id) {
		e.setId(id);
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

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_239", "Estudiante actualizado parcialmente");

		return ResponseEntity.status(239).headers(cabeceras).body(e2);
	}

	// DELETE
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_240", "Estudiante borrado exitosamente");

		return ResponseEntity.status(240).headers(cabeceras).body("Borrada exitosamente");
	}

	// GET by ID
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
		Estudiante estudiante = this.estudianteService.buscar(id);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "El que busca encuentra");

		return ResponseEntity.status(236).headers(cabeceras).body(estudiante);
	}

	// GET by genero
	@GetMapping(path = "/genero", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Estudiante> buscarPorGenero(@RequestParam String genero) {
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

	// GET mixto
	@GetMapping(path = "/mixto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Estudiante buscarMixto(@PathVariable Integer id) {
		return this.estudianteService.buscar(id);
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/texto/plano
	@GetMapping(path = "/texto/plano")
	public String prueba() {
		String prueba = "texto de prueba";
		return prueba;
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/hateoas/3
	@GetMapping(path = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudianteTO buscarHateoas(@PathVariable Integer id) {
		EstudianteTO estudiante = this.estudianteService.buscarPorId(id);
		Link link = linkTo(methodOn(EstudianteController.class).buscarmateriasPorIdEstudiante(id))
				.withRel("susMaterias");
		estudiante.add(link);

		Link link2 = linkTo(methodOn(EstudianteController.class).buscarPorId(id)).withRel("susMaterias");
		estudiante.add(link2);
		// ERROR, esto es una carga EAGER
//		List<MateriaTO> lista = this.iMateriaService.buscarPorIdEstudiante(id);
//		estudiante.setMaterias(lista);
		return estudiante;

	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/hateoas/3/materias
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaTO> buscarmateriasPorIdEstudiante(@PathVariable Integer id) {
		return this.iMateriaService.buscarPorIdEstudiante(id);
	}
}
