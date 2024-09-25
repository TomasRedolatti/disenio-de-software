package com.tp2.Repositories;

import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.time.LocalDate;
import java.util.List;

public interface EstudianteRepository extends BaseRepository<Estudiante, Long> {
    /*
        Metodos especificos de la clase estudiante. Por ej: buscar por LU
     */

    public Estudiante findByDNI(Long DNI);

    public List<Estudiante> findAllOrderByName();

    public List<Estudiante> findAllByGenero(Character genero);

    public Inscripcion setGraduado(Estudiante e, Carrera c, LocalDate fecha);
}
