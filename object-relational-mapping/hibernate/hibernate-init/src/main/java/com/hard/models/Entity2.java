package com.hard.models;

import javax.persistence.*;

@Entity
@Table(name = "entities2")
public class Entity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
}
