package server.model;


import server.entity.Book;

import java.util.Collection;
import java.util.Date;

public class BookModel {

    private Integer id;

    private String title;

    private String ISBN;

    private Date publicationDate;

    private AuthorModel[] authors ;

    public BookModel()
    {}

    public BookModel(String title, String ISBN, Date publicationDate)
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

    public AuthorModel[] getAuthors() {
        if (authors == null) {
            this.setAuthors(new AuthorModel[1]);
        }
        return authors;
    }

    public void setAuthors(AuthorModel[] authors) {
        this.authors = authors;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public static BookModel createFromEntity(Book bookEntity, int level)
    {
        BookModel bookModel = new BookModel(bookEntity.getTitle(), bookEntity.getISBN(), bookEntity.getPublicationDate());
        if(bookEntity.getId() != null && bookEntity.getId() > 0) {
            bookModel.setId(bookEntity.getId());
        }
        if (level > 0) {
            Collection<server.entity.Author> authorsEntity = bookEntity.getAuthors();
            AuthorModel[] authors = new AuthorModel[authorsEntity.size()];
            int index = 0;
            for(server.entity.Author author: authorsEntity) {
                authors[index] = AuthorModel.createFromEntity(author, level-1);
                index++;
            }
            bookModel.setAuthors(authors);
        }
        return bookModel;
    }
}
