package com.ynov.nantes.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynov.nantes.soap.entity.Author;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Matthieu BACHELIER
 * @since 2020-10
 * @version 1.0
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {

   /// TODO les méthodes pertinents en plus des méthodes CRUD (autogénérées), type findByName

}