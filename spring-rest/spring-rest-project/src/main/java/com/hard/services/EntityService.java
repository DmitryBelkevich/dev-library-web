package com.hard.services;

import com.hard.models.Entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

                if (entity.getId() == 0)
                    entity.setId(id);

                entities.add(entity);

                break;
            }
        }
    }

    public void updateCollection(Collection<Long> ids, Collection<Entity> entities) {
        Iterator<Long> idIterator = ids.iterator();
        Iterator<Entity> entityIterator = entities.iterator();
        Map<Long, Entity> idsEntities = IntStream.range(0, ids.size()).boxed()
                .collect(Collectors.toMap(_i -> idIterator.next(), _i -> entityIterator.next()));

        Iterator<Map.Entry<Long, Entity>> iterator = idsEntities.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Entity> idEntity = iterator.next();

            long id = idEntity.getKey();
            Entity entity = idEntity.getValue();

            update(id, entity);
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
