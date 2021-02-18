package com.hard.services;

import com.hard.models.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EntityService {
    private Collection<Entity> entities = new ArrayList<>();

    public EntityService() {
//        for (long i = 1; i <= 10; i++) {
//            Entity entity = new Entity();
//
//            entity.setId(i);
//            entity.setTitle("Entity " + i);
//
//            entities.add(entity);
//        }
    }

    public Collection<Entity> getAll() {
        return entities;
    }

    public Entity getById(long id) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.getId() == id)
                return entity;
        }

        return null;
    }
}
