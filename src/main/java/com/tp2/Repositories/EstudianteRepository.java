package com.tp2.Repositories;

import com.tp2.entity.Estudiante;

import java.util.List;

public interface EstudianteRepository extends BaseRepository<Estudiante, Long> {
    /*
        Metodos especificos de la clase estudiante. Por ej: buscar por LU
     */

    public Estudiante findByDNI(Long DNI);

    public List<Estudiante> findAllOrderByName();

    public List<Estudiante> findAllByGenero(Character genero);
}
