package com.tp2.Repositories;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<Entity, ID extends Serializable> {
        public Entity persist(Entity entity);

        public void merge(Entity entity);

        public void delete(Entity entity);

        public List<Entity> findAll();

        public Entity findById(ID id);
}
