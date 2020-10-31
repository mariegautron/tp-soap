package server.ws;

import server.model.BookModel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IBook {

    @WebMethod
    BookModel addBook(@WebParam(name="Book") BookModel book);

    @WebMethod
    BookModel deleteBook(@WebParam(name="BookId")int bookId);

    @WebMethod
    BookModel[] getAllBooks();

    @WebMethod
    BookModel[] getBookByName(@WebParam(name="BookName")String bookName);

    @WebMethod
    BookModel updateBook(@WebParam(name="Book")BookModel book);

    @WebMethod
    BookModel getBook(@WebParam(name="BookId")int bookId);

    @WebMethod
    BookModel[] getBooksByAuthorName(@WebParam(name="authorLastName")String authorLastName, @WebParam(name="authorFirstName") String authorFirstName);

    @WebMethod
    BookModel[] getBooksWithNoAuthor();

    @WebMethod
    BookModel[] getBooksRecently(@WebParam(name="startDate")Date startDate);
}
