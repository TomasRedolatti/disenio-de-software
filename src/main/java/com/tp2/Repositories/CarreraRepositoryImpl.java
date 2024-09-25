package com.tp2.Repositories;

import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.time.LocalDate;
import java.util.*;

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
        String query = "SELECT c FROM Carrera c ORDER BY c.nombre";
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

    public void getReporteCarreras() {

        // Consulta de inscriptos por carrera y año de inscripción
        String queryInscriptos = "SELECT c.nombre AS carrera, " +
                "YEAR(i.fechaAlta) AS anio, " +
                "COUNT(i) AS totalInscriptos " +
                "FROM Carrera c " +
                "JOIN c.estudiantes i " +
                "GROUP BY c.nombre, YEAR(i.fechaAlta) " +
                "ORDER BY c.nombre ASC, YEAR(i.fechaAlta) ASC";

        // Consulta de graduados por carrera y año de graduacion
        String queryGraduados = "SELECT c.nombre AS carrera, " +
                "YEAR(i.fechaFin) AS anio, " +
                "COUNT(i) AS totalGraduados " +
                "FROM Carrera c " +
                "JOIN c.estudiantes i " +
                "WHERE i.graduado = TRUE " +
                "AND i.fechaFin IS NOT NULL " +
                "GROUP BY c.nombre, YEAR(i.fechaFin) " +
                "ORDER BY c.nombre ASC, YEAR(i.fechaFin) ASC";

        List<Object[]> inscriptos = RepositoryFactory.getEntity_manager().createQuery(queryInscriptos).getResultList();
        List<Object[]> graduados = RepositoryFactory.getEntity_manager().createQuery(queryGraduados).getResultList();

        // Mapa que almacenará las carreras con los años y los totales
        Map<String, Map<Integer, Object[]>> reporteCarreras = new LinkedHashMap<>();

        // Procesar los inscriptos
        for (Object[] fila : inscriptos) {
            String carrera = (String) fila[0];
            Integer anio = (Integer) fila[1];
            Long totalInscriptos = (Long) fila[2];

            // Si la carrera no está en el mapa, la añadimos
            reporteCarreras.putIfAbsent(carrera, new LinkedHashMap<>());
            // Si el año no está en el mapa de esa carrera, añadimos un nuevo array [inscriptos, graduados]
            reporteCarreras.get(carrera).putIfAbsent(anio, new Object[] { 0L, 0L });
            // Actualizamos el total de inscriptos para ese año
            reporteCarreras.get(carrera).get(anio)[0] = totalInscriptos;
        }

        // Procesar los graduados
        for (Object[] fila : graduados) {
            String carrera = (String) fila[0];
            Integer anio = (Integer) fila[1];
            Long totalGraduados = (Long) fila[2];

            reporteCarreras.putIfAbsent(carrera, new LinkedHashMap<>());
            reporteCarreras.get(carrera).putIfAbsent(anio, new Object[] { 0L, 0L });
            // Actualizamos el total de graduados para ese año
            reporteCarreras.get(carrera).get(anio)[1] = totalGraduados;
        }

        //Impresión del reporte
        for (Map.Entry<String, Map<Integer, Object[]>> entryCarrera : reporteCarreras.entrySet()) {
            String carrera = entryCarrera.getKey();
            System.out.println("Carrera: " + carrera);

            for (Map.Entry<Integer, Object[]> entryAnio : entryCarrera.getValue().entrySet()) {
                Integer anio = entryAnio.getKey();
                Long totalInscriptos = (Long) entryAnio.getValue()[0];
                Long totalGraduados = (Long) entryAnio.getValue()[1];

                System.out.println("  Año: " + anio);
                System.out.println("    Total de Inscriptos: " + totalInscriptos);
                System.out.println("    Total de Graduados: " + totalGraduados);
            }
            System.out.println("----------------------------------------");
        }

    }


}

