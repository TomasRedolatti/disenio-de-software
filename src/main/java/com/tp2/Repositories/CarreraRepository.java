package com.tp2.Repositories;

import com.tp2.entity.Carrera;

public interface CarreraRepository extends BaseRepository<Carrera, Long> {
    /*
        Metodos especificos de carrera
     */

    public Carrera findByName(String name);
}
