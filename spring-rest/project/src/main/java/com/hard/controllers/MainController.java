package com.hard.controllers;

import com.hard.models.Entity;
import com.hard.services.EntityService;
import org.springframework.http.HttpHeaders;
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
        Entity entity = entityService.getById(id);

        if (entity == null)
            httpStatus = HttpStatus.NOT_FOUND;
        else
            httpStatus = HttpStatus.OK;

        ResponseEntity responseEntity = ResponseEntity
                .status(httpStatus)
                .body(entity);

        return responseEntity;
    }
}
