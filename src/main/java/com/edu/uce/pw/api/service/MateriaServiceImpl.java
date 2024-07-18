package com.edu.uce.pw.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.api.repository.IMateriaRepository;
import com.edu.uce.pw.api.repository.model.Materia;
import com.edu.uce.pw.api.service.to.MateriaTO;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaRepository iMateriaRepository;

	@Override
	public void agregar(Materia materia) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.insertar(materia);
	}

	@Override
	public void modificar(Materia materia) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.actualizar(materia);
	}

	@Override
	public Materia buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.iMateriaRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.eliminar(id);
	}

	@Override
	public List<Materia> buscarPorCredito(Integer credito) {
		// TODO Auto-generated method stub
		return this.iMateriaRepository.buscarPorCredito(credito);
	}

	@Override
	public List<MateriaTO> buscarPorIdEstudiante(Integer id) {
		// TODO Auto-generated method stub
		List<Materia> lista = this.iMateriaRepository.seleccionarPorIdEstudiante(id);

		List<MateriaTO> listaFinal = new ArrayList<>();

		for (Materia mat : lista) {
			listaFinal.add(this.convertir(mat));

		}
		return listaFinal;
	}

	private MateriaTO convertir(Materia materia) {
		MateriaTO materiaTO = new MateriaTO();
		materiaTO.setId(materia.getId());
		materiaTO.setNombre(materia.getNombre());
		materiaTO.setCreditos(materia.getCreditos());
		materiaTO.setPrecio(materia.getPrecio());
		materiaTO.setSemestre(materia.getSemestre());
		materiaTO.setProfesor(materia.getProfesor());
		return materiaTO;

	}
}