package com.tp2.Repositories;

import com.tp2.entity.Carrera;

import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {
    public static CarreraRepositoryImpl instance = new CarreraRepositoryImpl();
    private CarreraRepositoryImpl() {
    }

    public static CarreraRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public List<CarreraRepository> findByName(String name) {
        return List.of();
    }

    @Override
    public Carrera persist(Carrera carrera) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        if (carrera.getId() == null) {
            RepositoryFactory.getEntity_manager().persist(carrera);
            RepositoryFactory.getEntity_manager().getTransaction().commit();
            RepositoryFactory.cerrar_conexion();
            return carrera;
        }
        RepositoryFactory.getEntity_manager().merge(carrera);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        RepositoryFactory.cerrar_conexion();
        return carrera;
    }

    @Override
    public void merge(Carrera carrera) {

    }

    @Override
    public void delete(Carrera carrera) {

    }

    @Override
    public List<Carrera> findAll() {
        return List.of();
    }

    @Override
    public Carrera findById(Long aLong) {
        return null;
    }
}
