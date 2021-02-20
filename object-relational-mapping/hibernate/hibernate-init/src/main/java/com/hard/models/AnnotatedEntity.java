package com.hard.models;

import javax.persistence.*;

@Entity
@Table(name = "annotated_entities")
public class AnnotatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
}
