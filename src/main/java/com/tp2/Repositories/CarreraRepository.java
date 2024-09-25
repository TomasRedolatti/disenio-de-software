package com.tp2.Repositories;

import com.tp2.entity.Carrera;
import com.tp2.entity.Estudiante;
import com.tp2.entity.Inscripcion;

import java.util.List;
import java.util.Set;

public interface CarreraRepository extends BaseRepository<Carrera, Long> {
    /*
        Metodos especificos de carrera
     */

    public Carrera findByName(String name);

    public Inscripcion matricularEstudiante(Estudiante estudiante, Carrera carrera);

    public Set<Inscripcion> matricularEstudiantes(List<Estudiante> estudiantes, Carrera carrera);
}
