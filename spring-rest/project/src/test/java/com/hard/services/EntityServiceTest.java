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
        int id = 11;
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
        Collection<Entity> entities1 = new ArrayList();

        for (int i = count + 1; i <= count * 2; i++) {
            Entity entity = new Entity();

            entity.setId(i);
            entity.setTitle("Entity " + i);

            entities1.add(entity);
        }

        entityService.addCollection(entities1);

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
        Entity entity = new Entity();

        entity.setId(count + 1);
        entity.setTitle("Entity " + (count + 1));

        entityService.update(1, entity);

        Collection<Entity> entities = entityService.getAll();
        assertEquals(count, entities.size());

        entity = entityService.get(count + 1);
        assertEquals(count + 1, entity.getId());
        assertEquals("Entity " + (count + 1), entity.getTitle());
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

        assertEquals(10, entities.size());
    }

    @Test
    @DisplayName("delete")
    public void shouldDelete() {
        int id = 5;
        entityService.delete(id);

        Collection<Entity> entities = entityService.getAll();

        assertEquals(9, entities.size());

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
