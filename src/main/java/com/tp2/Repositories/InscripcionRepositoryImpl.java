package com.tp2.Repositories;

import com.tp2.entity.Inscripcion;

import java.util.List;

public class InscripcionRepositoryImpl implements BaseRepository<Inscripcion, Long> {
    public static InscripcionRepositoryImpl inscripcionRepository = new InscripcionRepositoryImpl();
    private InscripcionRepositoryImpl() {}

    public static InscripcionRepositoryImpl getInstance() {
        return inscripcionRepository;
    }

    @Override
    public Inscripcion persist(Inscripcion inscripcion) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().persist(inscripcion);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        RepositoryFactory.cerrar_conexion();
        return inscripcion;

    }

    @Override
    public Inscripcion merge(Inscripcion inscripcion) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().merge(inscripcion);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return inscripcion;
    }

    @Override
    public void delete(Inscripcion inscripcion) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().remove(inscripcion);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
    }

    @Override
    public List<Inscripcion> findAll() {
        String query = "SELECT i FROM Inscripcion i";
        return RepositoryFactory.getEntity_manager()
                .createQuery(query, Inscripcion.class)
                .getResultList();
    }

    @Override
    public Inscripcion findById(Long aLong) {
        return RepositoryFactory.getEntity_manager()
                .find(Inscripcion.class, aLong);
    }
}
