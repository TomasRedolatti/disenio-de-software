package com.tp2.Repositories;

import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CarreraRepositoryImpl implements CarreraRepository {
    public static CarreraRepositoryImpl instance = new CarreraRepositoryImpl();
    private CarreraRepositoryImpl() {
    }

    public static CarreraRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public Carrera findByName(String name) {
        String query = "SELECT c FROM Carrera c WHERE c.nombre = ?1";
        return RepositoryFactory.getEntity_manager()
                .createQuery(query, Carrera.class)
                .setParameter(1, name)
                .getSingleResult();
    }

    @Override
    public Carrera persist(Carrera carrera) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().persist(carrera);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return carrera;
    }



    @Override
    public void delete(Carrera carrera) {
        RepositoryFactory.getEntity_manager().remove(carrera);
    }

    @Override
    public List<Carrera> findAll() {
        String query = "SELECT c FROM Carrera c";
        return RepositoryFactory.getEntity_manager()
                .createQuery(query, Carrera.class)
                .getResultList();
    }

    public Carrera merge(Carrera carrera) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().merge(carrera);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return carrera;
    }

    @Override
    public Carrera findById(Long aLong) {
        return RepositoryFactory.getEntity_manager()
                .find(Carrera.class, aLong);
    }

    @Override
    public Inscripcion matricularEstudiante(Estudiante estudiante, Carrera carrera) {
        Inscripcion i = new Inscripcion(estudiante, carrera, LocalDate.now());
        carrera.addInscripciones(i);
        RepositoryFactory.get_repositorio_carrera().merge(carrera);

        return i;
    }

    @Override
    public Set<Inscripcion> matricularEstudiantes(List<Estudiante> estudiantes, Carrera carrera) {
        for (Estudiante e : estudiantes) {
            matricularEstudiante(e, carrera);
        }
        RepositoryFactory.get_repositorio_carrera().merge(carrera);
        return carrera.getEstudiantes();
    }

    @Override
    public List<Carrera> carrerasSegunInscriptos() {
        String query = "SELECT c " +
                "FROM Carrera c JOIN c.estudiantes " +
                "GROUP BY c " +
                "ORDER BY count(c) DESC";
        return RepositoryFactory.getEntity_manager().createQuery(query,Carrera.class)
                .getResultList();
    }

    @Override
    public List<Estudiante> getEstudiantesByResidencia(String residencia, Carrera carrera) {
        String query = "SELECT e " +
                "FROM Estudiante e " +
                "JOIN e.carreras i " +
                "WHERE e.residencia = ?1 " +
                "AND i.carrera = ?2";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class)
                .setParameter(1, residencia)
                .setParameter(2, carrera)
                .getResultList();
    }
}
