package com.ynov.nantes.soap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité Auteur persistente en base de données.
 * 
 * @author Matthieu BACHELIER
 * @since 2020-10
 * @version 1.0
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    /// TODO les autres champs
}