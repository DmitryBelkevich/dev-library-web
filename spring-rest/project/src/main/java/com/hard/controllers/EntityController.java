package com.hard.controllers;

import com.hard.models.Entity;
import com.hard.services.EntityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/entities")
public class EntityController {
    private EntityService entityService;

    public EntityController() {
        Collection<Entity> entities = new ArrayList<>();

        for (long i = 1; i <= 10; i++) {
            Entity entity = new Entity();

            entity.setId(i);
            entity.setTitle("Entity " + i);

            entities.add(entity);
        }

        entityService = new EntityService(entities);
    }

    /**
     * get
     */

    @GetMapping(value = "", produces = (MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
    public ResponseEntity<Collection<Entity>> getAll() {
        HttpStatus httpStatus;
        HttpHeaders headers = new HttpHeaders();
        Collection<Entity> entities = entityService.getAll();

        if (entities == null || entities.isEmpty())
            httpStatus = HttpStatus.NO_CONTENT;
        else
            httpStatus = HttpStatus.OK;

        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(entities);

        return responseEntity;
    }

    @GetMapping(value = "/{id}", produces = (MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
    public ResponseEntity<Entity> getById(@PathVariable("id") long id) {
        HttpStatus httpStatus;
        Entity entity = entityService.get(id);

        if (entity == null)
            httpStatus = HttpStatus.NOT_FOUND;
        else
            httpStatus = HttpStatus.OK;

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entity);

        return responseEntity;
    }

    /**
     * add
     */

    @PostMapping("")
    public ResponseEntity<Entity> add(@RequestBody Entity entity) {
        HttpStatus httpStatus;
        Entity e = entityService.get(entity.getId());

        if (e != null) {
            httpStatus = HttpStatus.CONFLICT;
            entity = null;
        } else {
            httpStatus = HttpStatus.CREATED;
            entityService.add(entity);
        }

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entity);

        return responseEntity;
    }

    @PutMapping("")
    public ResponseEntity<Entity> addCollection(@RequestBody Collection<Entity> entities) {
        HttpStatus httpStatus;

        Collection<Entity> entitiesTo = new ArrayList<>();

        for (Entity entity : entities) {
            Entity e = entityService.get(entity.getId());

            if (e == null)
                entitiesTo.add(entity);
        }

        if (entitiesTo.size() == 0) {
            httpStatus = HttpStatus.NO_CONTENT;
        } else if (entitiesTo.size() < entities.size()) {
            httpStatus = HttpStatus.PARTIAL_CONTENT;
            entityService.addCollection(entitiesTo);
        } else {
            httpStatus = HttpStatus.CREATED;
            entityService.addCollection(entities);
        }

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entitiesTo);

        return responseEntity;
    }

    /**
     * update
     */

    @PostMapping("/{id}")
    public ResponseEntity<Entity> update(@PathVariable("id") long id, @RequestBody Entity entity) {
        HttpStatus httpStatus;
        Entity e = entityService.get(id);

        if (e == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            entity = null;
        } else {
            httpStatus = HttpStatus.OK;
            entityService.update(id, entity);
        }

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entity);

        return responseEntity;
    }

    @PutMapping("/{ids}")
    public ResponseEntity<Collection<Entity>> updateCollection(@PathVariable("ids") Collection<Long> ids, @RequestBody Collection<Entity> entities) {
        HttpStatus httpStatus;

        Collection<Entity> entitiesTo = new ArrayList<>();

        for (Entity entity : entities) {
            Entity e = entityService.get(entity.getId());

            if (e != null)
                entitiesTo.add(entity);
        }

        if (entitiesTo.size() == 0) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (entitiesTo.size() < entities.size()) {
            httpStatus = HttpStatus.PARTIAL_CONTENT;
            entityService.updateCollection(null, entitiesTo);
        } else {
            httpStatus = HttpStatus.OK;
            entityService.updateCollection(ids, entities);
        }

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entitiesTo);

        return responseEntity;
    }

    /**
     * delete
     */

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll() {
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        entityService.deleteAll();

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(null);

        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        HttpStatus httpStatus;
        Entity entity = entityService.get(id);

        if (entity == null)
            httpStatus = HttpStatus.NOT_FOUND;
        else {
            entityService.delete(id);
            httpStatus = HttpStatus.NO_CONTENT;
        }

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(null);

        return responseEntity;
    }
}
