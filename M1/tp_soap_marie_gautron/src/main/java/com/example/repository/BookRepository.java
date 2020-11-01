package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ynov.nantes.soap.book.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	

}
