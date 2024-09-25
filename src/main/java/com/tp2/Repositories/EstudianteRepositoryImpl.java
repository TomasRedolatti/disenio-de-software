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
        RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class)
                .setParameter(1, DNI)
                .getSingleResult();
        return null;
    }

    @Override
    public Estudiante persist(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().getTransaction().begin();
        if (RepositoryFactory.get_repositorio_estudiante().findById(estudiante.getLU()) == null) {
            RepositoryFactory.getEntity_manager().persist(estudiante);
            RepositoryFactory.getEntity_manager().getTransaction().commit();
            return estudiante;
        }
        RepositoryFactory.getEntity_manager().merge(estudiante);
        RepositoryFactory.getEntity_manager().getTransaction().commit();
        return estudiante;
    }

    @Override
    public void delete(Estudiante estudiante) {
        RepositoryFactory.getEntity_manager().remove(estudiante);
    }

    @Override
    public List<Estudiante> findAll() {
        String query = "SELECT e FROM Estudiante e";
        return RepositoryFactory.getEntity_manager().createQuery(query, Estudiante.class).getResultList();
    }

    @Override
    public Estudiante findById(Long LU) {
        return RepositoryFactory.getEntity_manager().find(Estudiante.class, LU);
    }
}
