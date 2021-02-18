package com.hard.services;

import com.hard.models.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("getById")
    public void shouldGetById() {
        Entity entity = entityService.getById(0);

        assertNull(entity);

        for (int i = 1; i <= count; i++) {
            entity = entityService.getById(i);

            assertEquals(i, entity.getId());
            assertEquals("Entity " + i, entity.getTitle());
        }

        for (int i = count + 1; i <= 100; i++) {
            entity = entityService.getById(i);

            assertNull(entity);
        }
    }
}
