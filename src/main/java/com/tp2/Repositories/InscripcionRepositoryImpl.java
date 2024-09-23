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
        if (inscripcion.getId() == null) {
            RepositoryFactory.getEntity_manager().persist(inscripcion);
            RepositoryFactory.getEntity_manager().getTransaction().commit();
            RepositoryFactory.cerrar_conexion();
            return inscripcion;
        }
        RepositoryFactory.getEntity_manager().merge(inscripcion);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        RepositoryFactory.cerrar_conexion();
        return inscripcion;
    }

    @Override
    public void merge(Inscripcion inscripcion) {

    }

    @Override
    public void delete(Inscripcion inscripcion) {

    }

    @Override
    public List<Inscripcion> findAll() {
        return List.of();
    }

    @Override
    public Inscripcion findById(Long aLong) {
        return null;
    }
}
