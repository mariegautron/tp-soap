package server.ws;

import server.controller.BookController;
import server.controller.NotImplementedException;
import server.dao.Factory;
import server.entity.Book;
import server.model.BookModel;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface="server.ws.IBook")
public class BookImpl implements IBook {
    private final BookController controller = new BookController();

    @WebMethod
    public BookModel addBook(BookModel book) {
        BookModel ret = controller.add(book);
        Factory.getInstance().end();
        return ret;
    }

    @WebMethod
    public BookModel deleteBook(int bookId) {
        BookModel book = controller.delete(bookId);
        Factory.getInstance().end();
        return book;
    }

    @WebMethod
    public BookModel[] getAllBooks() {
        BookModel[] books = controller.getAll();
        Factory.getInstance().end();
        return books;
    }

    @WebMethod
    public BookModel[] getBookByName(String bookName) {
        BookModel[] books = controller.getBooksByName(bookName);
        Factory.getInstance().end();
        return books;
    }

    @WebMethod
    public BookModel updateBook(BookModel book) {
        try {
            return controller.update(book);
        } catch (NotImplementedException e) {
            return null;
        }
    }

    public BookModel getBook(int bookId) {
        return controller.getBook(bookId);
    }

    @WebMethod
    public BookModel[] getBooksByAuthorName(String authorLastName, String authorFirstName) {
        return controller.getBooksByAuthorName(authorLastName, authorFirstName);
    }


    @WebMethod
    public BookModel[] getBooksWithNoAuthor() {
        return controller.getBooksWithNoAuthor();
    }

    @WebMethod
    public BookModel[] getBooksRecently(Date startDate) {
        return controller.getBooksRecently(startDate);
    }
}
