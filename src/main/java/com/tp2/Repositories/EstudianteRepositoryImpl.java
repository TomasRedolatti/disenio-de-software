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
        String query = "SELECT e FROM Estudiante e WHERE e.dni = ?1";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class)
                .setParameter(1, DNI)
                .getSingleResult();
    }

    @Override
    public Estudiante persist(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().persist(estudiante);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return estudiante;
    }

    @Override
    public Estudiante merge(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().merge(estudiante);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        RepositoryFactory.getEntity_manager().remove(estudiante);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
    }

    @Override
    public List<Estudiante> findAll() {
        String query = "SELECT e FROM Estudiante e";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class).getResultList();
    }

    @Override
    public List<Estudiante> findAllOrderByName() {
        String query = "SELECT e FROM Estudiante e ORDER BY e.nombre";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class).getResultList();
    }

    @Override
    public Estudiante findById(Long LU) {
        return RepositoryFactory.getEntity_manager().find(Estudiante.class, LU);
    }

    @Override
    public List<Estudiante> findAllByGenero(Character genero) {
        String query = "SELECT e FROM Estudiante e WHERE e.genero = ?1";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class)
                .setParameter(1, genero)
                .getResultList();
    }
}
