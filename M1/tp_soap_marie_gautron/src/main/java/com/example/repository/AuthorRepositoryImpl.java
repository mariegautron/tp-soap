package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ynov.nantes.soap.author.Author;

@Component
public class AuthorRepositoryImpl {
	
	@Autowired
	private AuthorRepository repo;
	
	public Author findAuthorById(long id) {
		Assert.notNull(id, "The Author's name must not be null");
		return repo.findById(id).get();
	}

	public List<Author> getAllEntities() {
        List <Author> list = new ArrayList<>();
        repo.findAll().forEach(e -> list.add(e));
        return list;
	}

	public void deleteEntityById(long id) {
		repo.deleteById(id);
		
	}

	public void updateEntity(Author author) {
		repo.save(author);
	}

}
