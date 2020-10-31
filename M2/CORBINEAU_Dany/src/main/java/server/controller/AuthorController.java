package server.controller;

import org.hibernate.query.Query;
import server.dao.Factory;
import server.dao.RepositoryImpl;
import server.dao.RepositoryManager;

import server.entity.Author;
import server.entity.Book;
import server.model.AuthorModel;

import java.util.List;

public class AuthorController implements IController<AuthorModel>{
    RepositoryImpl<Author> authorRepository;

    public AuthorController()
    {
        authorRepository = RepositoryManager.getRepo(Author.class);
    }

    public AuthorModel getById(int id) {
        return AuthorModel.createFromEntity(authorRepository.get(id), 2);
    }

    private AuthorModel[] authorListToAuthorModelArray(List<Author> authors)
    {
        AuthorModel[] authorsModel = new AuthorModel[authors.size()];
        int index = 0;
        for(Author a : authors) {
            authorsModel[index] = AuthorModel.createFromEntity(a, 2);
            index++;
        }
        return authorsModel;
    }

    public AuthorModel[] getAll() {
         List<Author> authorsEntity = authorRepository.getAll();
        return authorListToAuthorModelArray(authorsEntity);
    }

    public AuthorModel update(AuthorModel author) {
        Author a = authorRepository.get(author.getId());
        if(a != null) {
            a.setFirstName(author.getFirstName());
            a.setLastName(author.getLastName());
            a.setBooks(Book.bookModeArrayToBookList(author.getBooks()));
            authorRepository.update(a);
            return author;
        } else {
            try{
                authorRepository.update(Author.createFromModel(author, 2));
            }catch (Exception e) {
                e.printStackTrace();
            }
            return author;
        }
    }

    public AuthorModel delete(int id) {
        Author author = authorRepository.get(id);
        AuthorModel authorModel = AuthorModel.createFromEntity(author, 2);
        authorRepository.del(author);
        return authorModel;
    }

    public AuthorModel add(AuthorModel object) {
        authorRepository.add(Author.createFromModel(object, 2));
        return object;
    }

    public AuthorModel[] getAuthorByName(String name) {
        Query query = Factory.getInstance().getSession().createQuery("select a from Author as a where a.lastName like :authorName or a.firstName like :authorName");
        query.setParameter("authorName", name);
        return authorListToAuthorModelArray(query.list());
    }
}
