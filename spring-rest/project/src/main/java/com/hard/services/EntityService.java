package com.hard.services;

import com.hard.models.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EntityService {
    private Collection<Entity> entities = new ArrayList<>();

    public EntityService() {

    }

    public EntityService(Collection<Entity> entities) {
        this.entities = entities;
    }

    public Collection<Entity> getAll() {
        return entities;
    }

    public Entity get(long id) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.getId() == id)
                return entity;
        }

        return null;
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void addCollection(Collection<Entity> entities) {
        this.entities.addAll(entities);
    }

    public void update(long id, Entity entity) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();

            if (e.getId() == id) {
                entities.remove(e);

                entity.setId(id);
                entities.add(entity);

                break;
            }
        }
    }

    public void updateCollection(Collection<Entity> entities) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            // TODO
        }
    }

    public void delete(long id) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.getId() == id) {
                entities.remove(entity);
                return;
            }
        }
    }

    public void deleteAll() {
        entities.clear();
    }
}
