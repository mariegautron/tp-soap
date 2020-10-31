package server.controller;

import org.hibernate.query.Query;
import server.dao.Factory;
import server.dao.RepositoryImpl;
import server.dao.RepositoryManager;
import server.entity.Author;
import server.entity.Book;
import server.model.BookModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookController implements IController<BookModel>{
    RepositoryImpl<Book> bookRepository;

    public BookController() {
        bookRepository = RepositoryManager.getRepo(Book.class);
    }

    public BookModel getById(int id) {
        return BookModel.createFromEntity(bookRepository.get(id), 2);
    }

    public BookModel[] getAll() {
        List<Book> booksEntity = bookRepository.getAll();
        return bookListToBookModelArray(booksEntity);
    }

    public BookModel update(BookModel book) throws NotImplementedException {
        Book b = bookRepository.get(book.getId());
        if(b != null) {
            b.setTitle(book.getTitle());
            b.setISBN(book.getISBN());
            b.setPublicationDate(book.getPublicationDate());
            b.setAuthors(Author.authorModeArrayToAuthorList(book.getAuthors()));
            bookRepository.update(b);
            return book;
        } else {
            try {
                bookRepository.update(Book.createFromModel(book, 2));
            }catch (Exception e) {
                e.printStackTrace();
            }
            return book;
        }
    }

    public BookModel delete(int id) {
        Book book = bookRepository.get(id);
        bookRepository.del(book);
        return BookModel.createFromEntity(book, 2);
    }

    public BookModel add(BookModel object) {
        bookRepository.add(Book.createFromModel(object, 2));
        return object;
    }

    public BookModel[] getBooksByName(String booksName)
    {
        /* TODO implement request */
        return new BookModel[1];
    }

    public BookModel[] getBooksByAuthorName(String authorLastName, String authorFirstName)
    {
        Query query = Factory.getInstance().getSession().createQuery("select b from Book as b left join b.authors a where a.lastName like '%"+authorLastName+"%' or a.firstName like '%"+authorFirstName+"%'");
        return bookListToBookModelArray(query.list());
    }

    public BookModel[] getBooksWithNoAuthor()
    {
        Query query = Factory.getInstance().getSession().createQuery("select b from Book as b left join b.authors a where a.id IS NULL");
        return bookListToBookModelArray(query.list());
    }

    public BookModel[] getBooksRecently(Date startDate) {
        Query query = Factory.getInstance().getSession().createQuery("select b from Book as b where b.publicationDate > :startDate");
        query.setParameter("startDate", startDate);
        return bookListToBookModelArray(query.list());
    }



    private BookModel[] bookListToBookModelArray(List books) {
        List<BookModel> bookList = new ArrayList<BookModel>();
        for(Object o : books) {
            if(o != null) {
                bookList.add(BookModel.createFromEntity((Book) o, 2));
            }
        }
        return bookList.toArray(new BookModel[0]);
    }

    public BookModel getBook(int id) {
        return BookModel.createFromEntity(bookRepository.get(id), 2);
    }
}
