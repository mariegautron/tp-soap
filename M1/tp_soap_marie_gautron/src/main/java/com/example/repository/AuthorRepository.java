package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ynov.nantes.soap.author.Author;



@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

	

}
