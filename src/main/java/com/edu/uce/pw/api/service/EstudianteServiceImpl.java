package com.edu.uce.pw.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.api.repository.IEstudianteRepository;
import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);

	}

	@Override
	public void registrar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);

	}

	@Override
	public List<Estudiante> seleccionarPorGenero(String genero) {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRepository.seleccionarPorGenero(genero);
		return lista;
	}

	public EstudianteTO convertir(Estudiante estu) {
		EstudianteTO esTo = new EstudianteTO();
		esTo.setId(estu.getId());
		esTo.setNombre(estu.getNombre());
		esTo.setApellido(estu.getApellido());
		esTo.setGenero(estu.getGenero());
		esTo.setFechaNacimiento(estu.getFechaNacimiento());
		esTo.setCedula(estu.getCedula());

		return esTo;

	}

	public List<EstudianteTO> convertirLista(List<Estudiante> estudiantes) {
		List<EstudianteTO> listaEstudiantesTO = new ArrayList<>();

		for (Estudiante estu : estudiantes) {
			EstudianteTO esTo = convertir(estu);
			listaEstudiantesTO.add(esTo);
		}

		return listaEstudiantesTO;
	}

	@Override
	public EstudianteTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Estudiante estu = this.estudianteRepository.seleccionar(id);
		return this.convertir(estu);
	}

	@Override
	public List<EstudianteTO> buscarTodos() {
		// TODO Auto-generated method stub
		List<Estudiante> estuLista = this.estudianteRepository.seleccionarTodos();
		return this.convertirLista(estuLista);
	}

	// *************************************NUEVO**********************************
	@Override
	public EstudianteTO buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		Estudiante estuCedula = this.estudianteRepository.seleccionarPorCedula(cedula);
		return this.convertir(estuCedula);
	}

	@Override
	public void actualizarPorCedula(EstudianteTO estudianteTO) {
		// TODO Auto-generated method stub

		Estudiante estudiante = this.convertirNormal(estudianteTO);
		this.estudianteRepository.actualizar(estudiante);

	}

	private Estudiante convertirNormal(EstudianteTO estudianteTO) {

		Estudiante e = new Estudiante();
		e.setApellido(estudianteTO.getApellido());
		e.setCedula(estudianteTO.getCedula());
		e.setFechaNacimiento(estudianteTO.getFechaNacimiento());
		e.setGenero(estudianteTO.getGenero());
		e.setId(estudianteTO.getId());
		e.setNombre(estudianteTO.getNombre());

		return e;

	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		try {

			this.estudianteRepository.eliminarPorCedula(cedula);
		} catch (Exception e) {
			// TODO: handle exception

			System.err.println("Error en la eliminarcion" + e);
		}

	}

	@Override
	public void agregar(EstudianteTO estudianteTO) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(this.convertirNormal(estudianteTO));

	}

}