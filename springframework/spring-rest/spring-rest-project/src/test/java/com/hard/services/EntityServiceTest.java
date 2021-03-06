package com.hard.services;

import com.hard.models.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntityServiceTest {
    private EntityService entityService;
    private int count = 10;

    @BeforeEach
    public void init() {
        entityService = new EntityService();

        for (int i = 1; i <= count; i++) {
            Entity entity = new Entity();

            entity.setId(i);
            entity.setTitle("Entity " + i);

            entityService.add(entity);
        }
    }

    /**
     * get
     */

    @Test
    @DisplayName("getAll")
    public void shouldGetAll() {
        Collection<Entity> entities = entityService.getAll();

        assertEquals(count, entities.size());

        int i = 1;
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());

            i++;
        }
    }

    @Test
    @DisplayName("get")
    public void shouldGet() {
        Entity entity = entityService.get(0);

        assertNull(entity);

        for (int i = 1; i <= count; i++) {
            entity = entityService.get(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }

        for (int i = count + 1; i <= 100; i++) {
            entity = entityService.get(i);

            assertNull(entity);
        }
    }

    /**
     * add
     */

    @Test
    @DisplayName("add")
    public void shouldAdd() {
        int id = count + 1;
        Entity entity = new Entity();

        entity.setId(id);
        entity.setTitle("Entity " + id);

        entityService.add(entity);

        Collection<Entity> entities = entityService.getAll();

        assertEquals(count + 1, entities.size());

        for (int i = 1; i <= count + 1; i++) {
            entity = entityService.get(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }
    }

    @Test
    @DisplayName("addCollection")
    public void shouldAddCollection() {
        Collection<Entity> entitiesTo = new ArrayList();

        for (int i = count + 1; i <= count * 2; i++) {
            Entity entity = new Entity();

            entity.setId(i);
            entity.setTitle("Entity " + i);

            entitiesTo.add(entity);
        }

        entityService.addCollection(entitiesTo);

        Collection<Entity> entities = entityService.getAll();

        assertEquals(count * 2, entities.size());

        for (int i = 1; i <= count * 2; i++) {
            Entity entity = entityService.get(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }
    }

    /**
     * update
     */

    @Test
    @DisplayName("update")
    public void shouldUpdate() {
        for (int i = 1; i <= count; i++) {
            Entity entity = new Entity();

            entity.setId(count + i);
            entity.setTitle("Entity " + (count + i));

            entityService.update(i, entity);
        }

        Collection<Entity> entities = entityService.getAll();
        assertEquals(count, entities.size());

        for (int i = 1; i <= count; i++) {
            Entity entity = entityService.get(count + i);
            assertEquals(count + i, entity.getId());
            assertEquals("Entity " + (count + i), entity.getTitle());
        }
    }

    @Test
    @DisplayName("updateCollection")
    public void shouldUpdateCollection() {
        Collection<Long> ids = new ArrayList<>();
        Collection<Entity> entitiesTo = new ArrayList<>();

        for (long i = 1; i <= count; i++) {
            Entity entity = new Entity();

            entity.setId(count + i);
            entity.setTitle("Entity " + (count + i));

            ids.add(i);
            entitiesTo.add(entity);
        }

        entityService.updateCollection(ids, entitiesTo);

        Collection<Entity> entities = entityService.getAll();
        assertEquals(count, entities.size());

        for (long i = 1; i <= count; i++) {
            Entity entity = entityService.get(i);
            assertNull(entity);
        }

        for (long i = count + 1; i <= count * 2; i++) {
            Entity entity = entityService.get(i);
            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }
    }

    /**
     * delete
     */

    @Test
    @DisplayName("deleteAll")
    public void shouldDeleteAll() {
        entityService.deleteAll();

        Collection<Entity> entities = entityService.getAll();

        assertEquals(0, entities.size());
    }

    @Test
    @DisplayName("delete nothing")
    public void shouldNotDelete() {
        int id = 0;
        entityService.delete(id);

        Collection<Entity> entities = entityService.getAll();

        assertEquals(count, entities.size());
    }

    @Test
    @DisplayName("delete")
    public void shouldDelete() {
        int id = 5;
        entityService.delete(id);

        Collection<Entity> entities = entityService.getAll();

        assertEquals(count - 1, entities.size());

        Entity entity;
        for (int i = 1; i <= 4; i++) {
            entity = entityService.get(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }

        entity = entityService.get(id);
        assertNull(entity);

        for (int i = 6; i <= count; i++) {
            entity = entityService.get(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }
    }
}
