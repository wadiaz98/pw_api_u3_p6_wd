package com.edu.uce.pw.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.uce.pw.api.repository.model.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRespositoryImpl implements IEstudianteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Estudiante seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public List<Estudiante> seleccionarPorGenero(String genero) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> myqueQuery = this.entityManager
				.createQuery("select e from Estudiante e where e.genero=:genero", Estudiante.class);
		myqueQuery.setParameter("genero", genero);
		return myqueQuery.getResultList();
	}

	@Override
	public List<Estudiante> seleccionarTodos() {
		// TODO Auto-generated method stub
		Query myquerQuery = this.entityManager.createNativeQuery("SELECT * FROM Estudiante", Estudiante.class);
		return myquerQuery.getResultList();
	}

	// ***************************************NUEVAS
	// Funcionalidad********************************************************* /
	@Override
	public Estudiante seleccionarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> query = this.entityManager
				.createQuery("SELECT e FROM Estudiante e WHERE e.cedula=:cedula", Estudiante.class);
		query.setParameter("cedula", cedula);
		return query.getSingleResult();

	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// Encontrar el estudiante por cédula
		TypedQuery<Estudiante> estudianteQuery = entityManager
				.createQuery("SELECT e FROM Estudiante e WHERE e.cedula = :cedula", Estudiante.class);
		estudianteQuery.setParameter("cedula", cedula);

		Estudiante estudiante;
		try {
			estudiante = estudianteQuery.getSingleResult();
		} catch (NoResultException e) {
			// Si no se encuentra ningún estudiante con la cédula proporcionada
			throw new EntityNotFoundException("No se encontró el estudiante con la cédula: " + cedula);
		}

		// Eliminar las materias relacionadas primero (asumiendo que la entidad Materia
		// tiene un campo 'estudiante' que es una relación many-to-one)
		Query deleteMateriasQuery = entityManager
				.createQuery("DELETE FROM Materia m WHERE m.estudiante.id = :estudianteId");
		deleteMateriasQuery.setParameter("estudianteId", estudiante.getId());
		deleteMateriasQuery.executeUpdate();

		// Eliminar el estudiante
		entityManager.remove(estudiante);

	}

}