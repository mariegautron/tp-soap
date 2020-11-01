package com.example.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import com.example.repository.AuthorRepository;
import com.ynov.nantes.soap.author.AddAuthorRequest;
import com.ynov.nantes.soap.author.AddAuthorResponse;
import com.ynov.nantes.soap.author.Author;
import com.ynov.nantes.soap.author.DeleteAuthorRequest;
import com.ynov.nantes.soap.author.DeleteAuthorResponse;
import com.ynov.nantes.soap.author.GetAllAuthorsResponse;
import com.ynov.nantes.soap.author.GetAuthorById;
import com.ynov.nantes.soap.author.GetAuthorByIdResponse;
import com.ynov.nantes.soap.author.GetAuthorRequest;
import com.ynov.nantes.soap.author.GetAuthorResponse;
import com.ynov.nantes.soap.author.UpdateAuthorRequest;
import com.ynov.nantes.soap.author.UpdateAuthorResponse;





@Endpoint
public class AuthorEndpoint {
	private static final String NAMESPACE_URI = "http://nantes.ynov.com/soap/author";

	private AuthorRepository authorRepository;

	@Autowired
	public AuthorEndpoint(AuthorRepository AuthorRepository) {
		this.authorRepository = AuthorRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorById")
	@ResponsePayload
	public GetAuthorByIdResponse getAuthorById(@RequestPayload GetAuthorById request) {
		GetAuthorByIdResponse response = new GetAuthorByIdResponse();
		response.setAuthor(authorRepository.findById((long) request.getId()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAuthorsRequest")
	public GetAllAuthorsResponse getAllAuthors() {
		GetAllAuthorsResponse response = new GetAllAuthorsResponse();
		List<Author> AuthorTypeList = new ArrayList<>();
		List<Author> AuthorList = authorRepository.getAllEntities();
		for (int i = 0; i < AuthorList.size(); i++) {
			Author author = new Author();
			AuthorTypeList.add(author);    
		}
		response.getAuthor().addAll(AuthorTypeList);
		return response;
}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAuthorRequest")
	public AddAuthorResponse addAuthor(@RequestPayload AddAuthorRequest request) {
		AddAuthorResponse response = new AddAuthorResponse();	
		Author author = new Author();
		author.setFirstName(request.getFirstName());
		author.setLastName(request.getLastName());

		Author AuthorInfo = new Author();
		BeanUtils.copyProperties(author, AuthorInfo);
		response.setAuthor(AuthorInfo);;

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAuthorRequest")
	public DeleteAuthorResponse deleteAuthors(@RequestPayload DeleteAuthorRequest request) {

		DeleteAuthorResponse response = new DeleteAuthorResponse();

		authorRepository.deleteById((long) request.getId());
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAuthorRequest")
	public UpdateAuthorResponse updateAuthors(@RequestPayload UpdateAuthorRequest request) {

		Author Author = new Author();
		authorRepository.updateEntity(Author);
		UpdateAuthorResponse response = new UpdateAuthorResponse();
		return response;
	}





}
