package server.ws;


import server.model.AuthorModel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IAuthor {
    @WebMethod
    AuthorModel addAuthor(@WebParam(name="Author") AuthorModel author);

    @WebMethod
    AuthorModel deleteAuthor(@WebParam(name="AuthorId")int authorId);

    @WebMethod
    AuthorModel[] getAllAuthors();

    @WebMethod
    AuthorModel[] getAuthorByName(@WebParam(name="AuthorName")String authorName);

    @WebMethod
    AuthorModel getAuthor(@WebParam(name="AuthorId")int authorId);

    @WebMethod
    AuthorModel updateAuthor(@WebParam(name="Author")AuthorModel author);
}
