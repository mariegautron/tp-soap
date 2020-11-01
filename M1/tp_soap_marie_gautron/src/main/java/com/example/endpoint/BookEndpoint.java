package com.example.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import com.example.repository.BookRepository;
import com.ynov.nantes.soap.book.AddBookRequest;
import com.ynov.nantes.soap.book.AddBookResponse;
import com.ynov.nantes.soap.book.Book;
import com.ynov.nantes.soap.book.DeleteBookRequest;
import com.ynov.nantes.soap.book.DeleteBookResponse;
import com.ynov.nantes.soap.book.GetAllBooksResponse;
import com.ynov.nantes.soap.book.GetBookById;
import com.ynov.nantes.soap.book.GetBookByIdResponse;
import com.ynov.nantes.soap.book.GetBookRequest;
import com.ynov.nantes.soap.book.GetBookResponse;
import com.ynov.nantes.soap.book.UpdateBookRequest;
import com.ynov.nantes.soap.book.UpdateBookResponse;





@Endpoint
public class BookEndpoint {
	private static final String NAMESPACE_URI = "http://nantes.ynov.com/soap/book";

	private BookRepository bookRepository;

	@Autowired
	public BookEndpoint(BookRepository BookRepository) {
		this.bookRepository = BookRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookById")
	@ResponsePayload
	public GetBookByIdResponse getBookById(@RequestPayload GetBookById request) {
		GetBookByIdResponse response = new GetBookByIdResponse();
		response.setBook(bookRepository.findById((long) request.getId()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
	public GetAllBooksResponse getAllBooks() {
		GetAllBooksResponse response = new GetAllBooksResponse();
		List<Book> BookTypeList = new ArrayList<>();
		List<Book> BookList = bookRepository.getAllEntities();
		for (int i = 0; i < BookList.size(); i++) {
			Book book = new Book();
			BookTypeList.add(book);    
		}
		response.getBookType().addAll(BookTypeList);
		return response;
}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
	public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
		AddBookResponse response = new AddBookResponse();	
		Book book = new Book();
		book.setTitle(request.getTitle());
		book.setIsbn(request.getISBN());

		Book BookInfo = new Book();
		BeanUtils.copyProperties(book, BookInfo);
		response.setBook(BookInfo);;

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
	public DeleteBookResponse deleteBooks(@RequestPayload DeleteBookRequest request) {

		DeleteBookResponse response = new DeleteBookResponse();

		bookRepository.deleteById((long) request.getId());
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBookRequest")
	public UpdateBookResponse updateBooks(@RequestPayload UpdateBookRequest request) {

		Book Book = new Book();
		bookRepository.updateEntity(Book);
		UpdateBookResponse response = new UpdateBookResponse();
		return response;
	}





}
