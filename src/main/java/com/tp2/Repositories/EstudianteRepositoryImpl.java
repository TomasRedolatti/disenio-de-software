package com.tp2.Repositories;

import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.time.LocalDate;
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

    public Inscripcion setGraduado(Estudiante e, Carrera c, LocalDate fecha) {
        Inscripcion i = (Inscripcion) RepositoryFactory.getEntity_manager()
                .createQuery("SELECT i FROM Inscripcion i " +
                        "WHERE i.estudiante = ?1 " +
                        "AND i.carrera = ?2")
                .setParameter(1, e)
                .setParameter(2, c)
                .getSingleResult();

        i.setGraduado(true);
        i.setFechaFin(fecha);

        RepositoryFactory.get_repositorio_inscripcion().merge(i);
        return i;
    }
}
