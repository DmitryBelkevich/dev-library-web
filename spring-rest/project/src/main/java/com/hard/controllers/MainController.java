package com.hard.controllers;

import com.hard.models.Entity;
import com.hard.services.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/entities")
public class MainController {
    private EntityService entityService = new EntityService();

    @GetMapping(value = "", produces = (MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
    public ResponseEntity<Collection<Entity>> getAll() {
        Collection<Entity> entities = entityService.getAll();

        ResponseEntity responseEntity = ResponseEntity
                .status(HttpStatus.OK)
                .body(entities);

        return responseEntity;
    }

    @GetMapping(value = "/{id}", produces = (MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
    public ResponseEntity<Entity> getById(@PathVariable("id") long id) {
        Entity entity = entityService.getById(id);

        ResponseEntity responseEntity = ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);

        return responseEntity;
    }
}
