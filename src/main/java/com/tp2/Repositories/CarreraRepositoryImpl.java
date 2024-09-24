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
    public Carrera findByName(String name) {
        String query = "SELECT c FROM Carrera c WHERE c.nome = ?1";
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
            RepositoryFactory.cerrar_conexion();
            return carrera;
        }
        RepositoryFactory.getEntity_manager().merge(carrera);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        RepositoryFactory.cerrar_conexion();
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

    @Override
    public Carrera findById(Long aLong) {
        return RepositoryFactory.getEntity_manager()
                .find(Carrera.class, aLong);
    }
}
