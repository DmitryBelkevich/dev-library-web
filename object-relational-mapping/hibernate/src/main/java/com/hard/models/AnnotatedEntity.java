package com.hard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "annotated_entities")
public class AnnotatedEntity {
    @Id
    private long id;
    private String title;
}
