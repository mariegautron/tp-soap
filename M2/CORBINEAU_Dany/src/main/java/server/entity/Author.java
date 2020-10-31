package server.entity;

import server.model.AuthorModel;
import server.model.BookModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Book> books;

    public Author(){}

    public Author(String firstName, String lastName){
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

    public Collection<Book> getBooks() {
        if (books == null) {
            this.setBooks(new ArrayList<Book>());
        }
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public static Author createFromModel(AuthorModel authorModel, int level)
    {
        Author authorEntity = new Author(authorModel.getFirstName(), authorModel.getLastName());
        if (authorModel.getId() != null && authorModel.getId() > 0) {
            authorEntity.setId(authorModel.getId());
        }
        if (level > 0) {
            List<Book> booksEntity = new ArrayList<Book>();
            for(BookModel bookModel : authorModel.getBooks()) {
                if(bookModel!=null) {
                    booksEntity.add(Book.createFromModel(bookModel, level-1));
                }
            }
            authorEntity.setBooks(booksEntity);
        }
        return authorEntity;
    }

    public static List<Author> authorModeArrayToAuthorList(AuthorModel[] authors) {
        List<Author> authorList = new ArrayList<Author>();
        for(AuthorModel author: authors) {
            if(author != null)
                authorList.add(Author.createFromModel(author, 2));
        }
        return authorList;
    }
}
