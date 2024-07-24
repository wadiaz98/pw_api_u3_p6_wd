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
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	// Interaccion directa con el service
	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService iMateriaService;

	// @RequestBoby: cuando se necesita enviar objetos entrada, se lo pone como
	// argumaneto del metodo
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	// Nivel 1 http://localhost:8080/API/v1.0/Matricula/estudiantes
	@PostMapping(produces = "application/json", consumes = "application/xml")
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante est) {

		HttpHeaders cabeceraPost = new HttpHeaders();
		cabeceraPost.add("mensaje_201", "Corresponde a la inserción de un recurso");
		cabeceraPost.add("valor", "Estudiante insertado con éxito");
		this.estudianteService.registrar(est);
		return new ResponseEntity<>(est, cabeceraPost, 201);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1 http://localhost:8082/API/v1.0/Matricula/estudiantes/{id}
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante est, @PathVariable Integer id) {
		est.setId(id);
		this.estudianteService.actualizar(est);
		// return ResponseEntity.status(238).body(est);

		HttpHeaders cabeceraPut = new HttpHeaders();
		cabeceraPut.add("mensaje_238", "Corresponde a la actualización de un recurso");
		cabeceraPut.add("valor", "Estudiante actualizado");
		return new ResponseEntity<>(est, cabeceraPut, 238);

	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// Nivel 1 http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	// En el postman ya no lleva el id
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Estudiante> actualizarParcial(@RequestBody Estudiante est, @PathVariable Integer id) {
		est.setId(id);
		Estudiante est2 = this.estudianteService.buscar(est.getId());
		if (est.getNombre() != null) {
			est2.setNombre(est.getNombre());
		}
		if (est.getApellido() != null) {
			est2.setApellido(est.getApellido());

		}
		if (est.getFechaNacimiento() != null) {
			est2.setFechaNacimiento(est.getFechaNacimiento());
		}

		this.estudianteService.actualizar(est2);
		HttpHeaders cabeceraPatch = new HttpHeaders();
		cabeceraPatch.add("mensaje_239", "Corresponde a la actualización parcial de un recurso");
		cabeceraPatch.add("valor", "Estudiante actualizado parcialmente");
		// return ResponseEntity.status(239).body(est2);
		return new ResponseEntity<>(est2, cabeceraPatch, 239);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar/2 puedo enviar
	// cualquier tipo de dato
	// Nivel 1 http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) { // el tipo de dato que voy a ocupar en este caso es
																		// un id por
		// por eso es un Integer y le pongo la anotacion @PathVariable

		this.estudianteService.borrar(id);
		HttpHeaders cabeceraDelete = new HttpHeaders();
		cabeceraDelete.add("mensaje_240", "Corresponde a la eliminación del recurso");
		cabeceraDelete.add("valor", "Estudiante eliminado");
		return new ResponseEntity<>("Eliminado correctamente", cabeceraDelete, 240);

	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1/nuevo/prueba
	// Nivel 1 http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
		// Estudiante es= this.estudianteService.buscar(id);
		// return ResponseEntity.status(236).body(es);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Estudiante escontrado");
		return new ResponseEntity<>(this.estudianteService.buscar(id), cabeceras, 236);
	}

	@GetMapping(path = "/genero")
	public List<Estudiante> buscarPorGenero(@RequestParam String genero) {

		List<Estudiante> lista = this.estudianteService.seleccionarPorGenero(genero);
		return lista;
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=F&edad=21
	@GetMapping(path = "/buscarPorGenero")
	// El elemento de fitrado viene en el arguemeto con la anotacion @RequestParam
	public List<Estudiante> buscarPorGenero(@RequestParam String genero, @RequestParam Integer edad) {
		System.out.println("Edad " + edad);
		List<Estudiante> lista = this.estudianteService.seleccionarPorGenero(genero);
		return lista;

	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/buscarMixto/3?prueba=HolaMundo
	@GetMapping(path = "/buscarMixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		System.out.println("Id " + id);
		System.out.println("Prueba " + prueba);
		return this.estudianteService.buscar(id);
	}

	// EL END POINT NO TIENE QUE SE AMBIGUO, EN DARSE ESTE CASO ME DA ERRORES
	// AMBIGUOS MAPPING
	// http://localhost:8082/API/v1.0/Matricula/estudiantes/texto/plano
	@GetMapping(path = "/texto/plano")
	public String prueba() {
		String prueba = "Texto de prueba";
		return prueba;

	}

	// **************************************************************************//
	// http://localhost:8082/API/v1.0/Matricula/estudiantes/hateoas/3
	@GetMapping(path = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudianteTO buscarHateoas(@PathVariable Integer id) {
		EstudianteTO estudiante = this.estudianteService.buscarPorId(id);
		// ERROR es una carga EAGER
		/*
		 * List<MateriaTO> lista= this.iMateriaService.buscarPorIdEstudiante(id);
		 * estudiante.setMaterias(lista);
		 */

		// PARA CREAR EL HIPERVINCULO USAMOS LA CLASE LINK
		Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(id))
				.withRel("sus materias");

		Link mylink2 = linkTo(methodOn(EstudianteController.class).buscarPorId(id)).withSelfRel();
		estudiante.add(myLink);
		estudiante.add(mylink2);
		return estudiante;

	}

	// CAPACIDAD
	// http://localhost:8082/API/v1.0/Matricula/estudiantes/2/materias
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaTO> buscarMateriaPorIdEstudiante(@PathVariable Integer id) {
		return this.iMateriaService.buscarPorIdEstudiante(id);
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstudianteTO> buscarTodos() {
		List<EstudianteTO> listaEstudiantes = this.estudianteService.buscarTodos();
		for (EstudianteTO estudianteTO : listaEstudiantes) {
			// PARA CREAR EL HIPERVINCULO USAMOS LA CLASE LINK
			Link myLink = linkTo(
					methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(estudianteTO.getId()))
					.withRel("sus materias");

			estudianteTO.add(myLink); // Assuming EstudianteTO extends ResourceSupport or RepresentationModel
		}
		return listaEstudiantes;
	}

	// ACTUALIZAR POR CEDULA
	// http://localhost:8082/API/v1.0/Matricula/estudiantes/1751451767/cedula
	@GetMapping(path = "/{cedula}/cedula")
	public EstudianteTO buscarPorCedula(@PathVariable String cedula) {
		return this.estudianteService.buscarPorCedula(cedula);
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/1751451767/cedula
	@DeleteMapping(path = "/{cedula}/cedula", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrarPorCedula(@PathVariable String cedula) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		this.estudianteService.eliminarPorCedula(cedula);
		return new ResponseEntity<>("Borrar", cabeceras, HttpStatus.OK);

	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/1751451767/cedula
	@PutMapping(path = "/{cedula}/buscarPorCedula", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> actualizarPorCedula(@RequestBody EstudianteTO estudianteTO,
			@PathVariable String cedula) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "actualizarPorCedula");
		EstudianteTO estudianteTO2 = this.estudianteService.buscarPorCedula(cedula);
		estudianteTO.setId(estudianteTO2.getId());

		this.estudianteService.actualizarPorCedula(estudianteTO);
		return new ResponseEntity<>(estudianteTO2, cabeceras, HttpStatus.OK);

	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/hateoas
	@PostMapping(path = "/hateoas", produces = "application/json", consumes = "application/json")
	public ResponseEntity<EstudianteTO> guardar(@RequestBody EstudianteTO est) {

		HttpHeaders cabeceraPost = new HttpHeaders();
		cabeceraPost.add("mensaje_201", "Corresponde a la inserción de un recurso");
		cabeceraPost.add("valor", "Estudiante insertado con éxito");
		this.estudianteService.agregar(est);

		EstudianteTO m = this.estudianteService.buscarPorCedula(est.getCedula());

		// System.out.println("estu: "+ m.getCedula().toString());
		// System.out.println("estu completo: "+m.toString());
		return new ResponseEntity<>(m, cabeceraPost, 201);
	}

}