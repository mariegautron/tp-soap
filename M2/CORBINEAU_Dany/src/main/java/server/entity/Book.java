package server.entity;


import server.model.AuthorModel;
import server.model.BookModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "publication_date")
    private Date publicationDate;

    @ManyToMany(mappedBy="books", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Author> authors ;

    public Book()
    {}

    public Book(String title, String ISBN, Date publicationDate)
    {
        this.title = title;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Collection<Author> getAuthors() {
        if (authors == null) {
            this.setAuthors(new ArrayList<Author>());
        }
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public static Book createFromModel(BookModel bookModel, int level) {
        Book bookEntity = new Book(bookModel.getTitle(), bookModel.getISBN(), bookModel.getPublicationDate());
        if (bookModel.getId() != null && bookModel.getId() > 0) {
            bookEntity.setId(bookModel.getId());
        }
        if (level > 0) {
            List<Author> authorsEntity = new ArrayList<Author>();
            for(AuthorModel authorModel : bookModel.getAuthors()) {
                if(authorModel != null) {
                    authorsEntity.add(Author.createFromModel(authorModel, level-1));
                }
            }
            bookEntity.setAuthors(authorsEntity);
        }
        return bookEntity;
    }

    public static List<Book> bookModeArrayToBookList(BookModel[] books) {
        List<Book> bookList = new ArrayList<Book>();
        for(BookModel book: books) {
            if(book != null)
                bookList.add(Book.createFromModel(book, 2));
        }
        return bookList;
    }
}
