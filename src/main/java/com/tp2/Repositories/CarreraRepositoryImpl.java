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
        if (carrera.getId() == null) {
            RepositoryFactory.getEntity_manager().persist(carrera);
            RepositoryFactory.getEntity_manager().getTransaction().commit();
            return carrera;
        }
        RepositoryFactory.getEntity_manager().merge(carrera);
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
        RepositoryFactory.getEntity_manager().merge(carrera);
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
        Inscripcion inscripcion;
        for (Estudiante e : estudiantes) {
            inscripcion = matricularEstudiante(e, carrera);
            carrera.addInscripciones(inscripcion);
        }
        RepositoryFactory.get_repositorio_carrera().merge(carrera);
        return carrera.getEstudiantes();
    }
}
