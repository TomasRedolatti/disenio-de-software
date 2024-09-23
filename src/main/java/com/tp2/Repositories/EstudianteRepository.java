package com.tp2.Repositories;

import com.tp2.entity.Estudiante;

public interface EstudianteRepository extends BaseRepository<Estudiante, Long> {
    /*
        Metodos especificos de la clase estudiante. Por ej: buscar por LU
     */

    public Estudiante findByDNI(Long DNI);
}
