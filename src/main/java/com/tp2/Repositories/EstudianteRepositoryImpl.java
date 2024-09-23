package com.tp2.Repositories;

import com.tp2.entity.Estudiante;

import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    public static EstudianteRepositoryImpl instance = new EstudianteRepositoryImpl();
    private EstudianteRepositoryImpl() {}

    public static EstudianteRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public Estudiante findByDNI(Long DNI) {

        return null;
    }

    @Override
    public Estudiante persist(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        if (estudiante.getLU() == null) {
            RepositoryFactory.getEntity_manager().persist(estudiante);
            RepositoryFactory.getEntity_manager().getTransaction().commit();
            RepositoryFactory.cerrar_conexion();
            return estudiante;
        }
        RepositoryFactory.getEntity_manager().merge(estudiante);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        RepositoryFactory.cerrar_conexion();
        return estudiante;
    }

    @Override
    public void merge(Estudiante estudiante) {

    }

    @Override
    public void delete(Estudiante estudiante) {

    }

    @Override
    public List<Estudiante> findAll() {
        return List.of();
    }

    @Override
    public Estudiante findById(Long LU) {
        return RepositoryFactory.getEntity_manager().find(Estudiante.class, LU);
    }
}
