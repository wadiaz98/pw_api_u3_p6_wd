package com.edu.uce.pw.api.service;

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
	public void guardar(Estudiante e) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(e);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante e) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(e);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public List<Estudiante> buscarPorGenero(String genero) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.buscarPorGenero(genero);
	}

	public EstudianteTO convertir(Estudiante estu) {
		EstudianteTO esTo = new EstudianteTO();
		esTo.setId(estu.getId());
		esTo.setNombre(estu.getNombre());
		esTo.setApellido(estu.getApellido());
		esTo.setGenero(estu.getGenero());
		esTo.setFechaNacimiento(estu.getFecha());
		return esTo;

	}

	@Override
	public EstudianteTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Estudiante estu = this.estudianteRepository.seleccionar(id);
		return this.convertir(estu);
	}
}
