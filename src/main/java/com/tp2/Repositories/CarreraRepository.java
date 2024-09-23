package com.tp2.Repositories;

import com.tp2.entity.Carrera;

import java.util.List;

public interface CarreraRepository extends BaseRepository<Carrera, Long> {
    /*
        Metodos especificos de carrera
     */

    public List<CarreraRepository> findByName(String name);
}
