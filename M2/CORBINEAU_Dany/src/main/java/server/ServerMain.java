package server;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import server.dao.Factory;
import server.dao.RepositoryImpl;
import server.dao.RepositoryManager;
import server.entity.Author;
import server.entity.Book;
import server.ws.AuthorImpl;
import server.ws.BookImpl;

import javax.xml.ws.Endpoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ServerMain {
    private static StandardServiceRegistry registry;

    public static void main(String[] args) {
        setUpHivernate();
        try {
            testDB();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Endpoint.publish("http://localhost:8081/bookImp", new BookImpl());
        Endpoint.publish("http://localhost:8081/authorImp", new AuthorImpl());

        downHibernate();
    }

    public static void setUpHivernate() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        Factory.getInstance();
    }

    public static void downHibernate() {
        Factory.getInstance().end();
        StandardServiceRegistryBuilder.destroy(registry);
    }

    public static void testDB() throws ParseException {
        RepositoryImpl<Author> repoAuthor =  RepositoryManager.getRepo(Author.class);
        RepositoryImpl<Book> repoBook =  RepositoryManager.getRepo(Book.class);

        Author a1 = new Author("Christophe", "Chatigny");
        Author a2 = new Author("Christian", "Laframboise");
        Author a3 = new Author("Senapus", "Faure");
        Author a4 = new Author("André ", "Henrichon");
        Author a5 = new Author("Tanguy", "Chassé");

        repoAuthor.add(a1);
        repoAuthor.add(a2);
        repoAuthor.add(a3);
        repoAuthor.add(a4);
        repoAuthor.add(a5);

        Book b1 = new Book("Chat sauvage", "ISBN-01",  new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));
        Book b2 = new Book("Pragmatique", "ISBN-02",  new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01"));
        Book b3 = new Book("L'étagère du dessus", "ISBN-03",  new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01"));
        Book b4 = new Book("Sapin", "ISBN-04",  new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"));
        Book b5 = new Book("Lune bleue", "ISBN-05",  new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01"));
        Book b6 = new Book("Le plus en avant", "ISBN-06",  new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"));
        Book b7 = new Book("Premier rang", "ISBN-07",  new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"));

        repoBook.add(b1);
        repoBook.add(b2);
        repoBook.add(b3);
        repoBook.add(b4);
        repoBook.add(b5);
        repoBook.add(b6);
        repoBook.add(b7);

        a1.getBooks().add(b1);
        a1.getBooks().add(b2);
        a2.getBooks().add(b1);
        a2.getBooks().add(b7);
        a3.getBooks().add(b3);
        a4.getBooks().add(b4);
        a4.getBooks().add(b5);
        a4.getBooks().add(b6);
        a5.getBooks().add(b5);
        a5.getBooks().add(b6);
        a5.getBooks().add(b7);

        repoAuthor.update(a1);
        repoAuthor.update(a2);
        repoAuthor.update(a3);
        repoAuthor.update(a4);
        repoAuthor.update(a5);

        List<Author> authors = repoAuthor.getAll();
        for(Author author : authors) {
            List<Book> authBooks = (List<Book>) author.getBooks();
            for(Book book : authBooks) {
                System.out.println(author.getFirstName()+" "+author.getLastName()+" have "+book.getTitle());
            }
        }

        System.out.println("------------ Inversor -------------------");

        List<Book> books = repoBook.getAll();
        for(Book book : books) {
            List<Author> bookAuths = (List<Author>) book.getAuthors();
            for(Author author : bookAuths) {
                System.out.println(book.getTitle()+" - " + book.getPublicationDate().toString() +" have "+author.getFirstName()+" "+author.getLastName());
            }
        }
        Factory.getInstance().end();
    }
}
