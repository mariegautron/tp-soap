package com.ynov.nantes.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ynov.nantes.soap.author.AddAuthorRequest;
import com.ynov.nantes.soap.author.AddAuthorResponse;
import com.ynov.nantes.soap.author.GetAuthorRequest;
import com.ynov.nantes.soap.author.GetAuthorResponse;
import com.ynov.nantes.soap.author.GetAuthorsRequest;
import com.ynov.nantes.soap.author.GetAuthorsResponse;
import com.ynov.nantes.soap.entity.Author;
import com.ynov.nantes.soap.repository.AuthorRepository;

@Endpoint
public class AuthorEndpoint {
    private static final String NAMESPACE_URI = "http://nantes.ynov.com/soap/author";

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorEndpoint(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorRequest")
    @ResponsePayload
    public GetAuthorResponse getAuthor(@RequestPayload GetAuthorRequest request) {
        /// TODO
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorsRequest")
    @ResponsePayload
    public GetAuthorsResponse getAuthors(@RequestPayload GetAuthorsRequest request) {
        /// TODO
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAuthorRequest")
    @ResponsePayload
    public AddAuthorResponse addAuthor(@RequestPayload AddAuthorRequest request) {

        /// TODO
        return null;
    }

}
