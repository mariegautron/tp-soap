package com.ynov.nantes.soap.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ynov.nantes.soap.book.AddBookRequest;
import com.ynov.nantes.soap.book.AddBookResponse;
import com.ynov.nantes.soap.book.DeleteBookRequest;
import com.ynov.nantes.soap.book.DeleteBookResponse;
import com.ynov.nantes.soap.book.GetBookByTitleRequest;
import com.ynov.nantes.soap.book.GetBookResponse;
import com.ynov.nantes.soap.book.GetBooksRequest;
import com.ynov.nantes.soap.book.GetBooksResponse;
import com.ynov.nantes.soap.entity.Author;
import com.ynov.nantes.soap.entity.Book;
import com.ynov.nantes.soap.repository.AuthorRepository;
import com.ynov.nantes.soap.repository.BookRepository;

@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://nantes.ynov.com/soap/book";

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEndpoint(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookByTitleRequest")
    @ResponsePayload
    public GetBookResponse getBookByTitle(@RequestPayload GetBookByTitleRequest request) {
        /// TODO
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks(@RequestPayload GetBooksRequest request) {
        /// TODO
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {

        /// TODO
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
    @ResponsePayload
    public DeleteBookResponse addBook(@RequestPayload DeleteBookRequest request) {

        /// TODO
        return null;
    }

    /**
     * Fonction de conversion d'objet de la base de données vers le WebService.
     * 
     * @param book objet de la base de donnée à transférer
     * @return le DTO
     */
    private com.ynov.nantes.soap.book.Book toDto(@NonNull Book book) {
        com.ynov.nantes.soap.book.Book bookDto = new com.ynov.nantes.soap.book.Book();
      /// TODO
        return null;
    }
}
