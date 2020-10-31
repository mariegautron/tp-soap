package server.model;


import server.entity.Author;
import server.entity.Book;

import java.util.Collection;

public class AuthorModel {
    private Integer id;

    private String firstName;

    private String lastName;

    private BookModel[] books;

    public AuthorModel(){}

    public AuthorModel(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BookModel[] getBooks() {
        if (books == null) {
            this.setBooks(new BookModel[0]);
        }
        return books;
    }

    public void setBooks(BookModel[] books) {
        this.books = books;
    }

    public static AuthorModel createFromEntity(Author authorEntity, int level){
        AuthorModel authorModel = new AuthorModel(authorEntity.getFirstName(), authorEntity.getLastName());
        if(authorEntity.getId() != null && authorEntity.getId()>0) {
            authorModel.setId(authorEntity.getId());
        }
        if(level > 0) {
            Collection<Book> booksEntity = authorEntity.getBooks();
            BookModel[] books = new BookModel[booksEntity.size()];
            int index = 0;
            for(Book book: booksEntity) {
                books[index] = BookModel.createFromEntity(book, level-1);
                index++;
            }
            authorModel.setBooks(books);
        }
        return authorModel;
    }
}
