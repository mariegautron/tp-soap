package client;

import server.model.AuthorModel;
import server.model.BookModel;
import server.ws.IAuthor;
import server.ws.IBook;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientMain {
    public static void main(String[] args) throws ParseException {
        Cli c = new Cli();
        c.run();
    }
    public static void clientQname() throws ParseException {
        testBook();
        testAuthor();
    }

    public static void testAuthor()
    {
        URL url = null;
        try {
            url = new URL("http://localhost:8081/authorImp?wsdl");
            QName qName = new QName("http://ws.server/", "AuthorImplService");
            Service ser = Service.create(url, qName);
            IAuthor authorPort = ser.getPort(IAuthor.class);

            AuthorModel[] as = authorPort.getAllAuthors();
            System.out.println("Nb author" + as.length);
            AuthorModel a = authorPort.addAuthor(new AuthorModel("Dany", "corbineau"));
            System.out.println("Add author "+a.getFirstName());
            as = authorPort.getAuthorByName("Dany");
            System.out.println("NB author by name " + as.length);
            as = authorPort.getAllAuthors();
            AuthorModel am = as[as.length-1];
            System.out.println("Nb author : "+as.length);
            System.out.println("To delete "+am.getFirstName()+" - "+am.getLastName());
            as = authorPort.getAllAuthors();
            System.out.println("Nb author : "+as.length);
            //System.out.println(authorPort.updateAuthor(456));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void testBook() throws ParseException {
        URL url = null;
        try {
            url = new URL("http://localhost:8081/bookImp?wsdl");
            QName qName = new QName("http://ws.server/", "BookImplService");
            Service ser = Service.create(url, qName);
            IBook bookPort = ser.getPort(IBook.class);

//            System.out.println(bookPort.getAllBooks());
//            System.out.println(bookPort.getBookByName("My Book name"));
//            System.out.println(bookPort.addBook(
//                    new BookModel("BName", "ISBN",  new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01")))
//            );
//            System.out.println(bookPort.deleteBook(852));
//            System.out.println(bookPort.updateBook(456));
//            System.out.println(bookPort.getBooksByAuthorName("Alpha", "Beta"));
//            System.out.println(bookPort.getBooksRecently( new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-01")));
//            System.out.println(bookPort.getBooksWithNoAuthor());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
