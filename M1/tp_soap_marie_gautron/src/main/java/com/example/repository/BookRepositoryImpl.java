package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.ynov.nantes.soap.book.Book;

@Component
public class BookRepositoryImpl {
	

	@Autowired
	private BookRepository repo;
	
	public Book findBookById(int id) {
		Assert.notNull(id, "The Book's name must not be null");
		return repo.findById((long) id).get();
	}

	public List<Book> getAllEntities() {
        List <Book> list = new ArrayList<>();
        repo.findAll().forEach(e -> list.add(e));
        return list;
	}

	public void deleteEntityById(int id) {
		repo.deleteById((long) id);
		
	}

	public void updateEntity(Book book) {
		repo.save(book);
	}

}
