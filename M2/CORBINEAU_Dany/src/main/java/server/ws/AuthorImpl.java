package server.ws;


import server.controller.AuthorController;
import server.dao.Factory;
import server.model.AuthorModel;

import javax.jws.WebService;

@WebService(endpointInterface="server.ws.IAuthor")
public class AuthorImpl implements IAuthor {
    private final AuthorController controller = new AuthorController();

    public AuthorModel addAuthor(AuthorModel author) {
        AuthorModel authorModel = controller.add(author);
        Factory.getInstance().end();
        return authorModel;
    }

    public AuthorModel deleteAuthor(int authorId) {

        AuthorModel authorModel = controller.delete(authorId);
        Factory.getInstance().end();
        return authorModel;
    }

    public AuthorModel[] getAllAuthors() {
        AuthorModel[] authors = controller.getAll();
        Factory.getInstance().end();
        return authors;
    }

    public AuthorModel[] getAuthorByName(String authorName) {

        return controller.getAuthorByName(authorName);
    }

    public AuthorModel getAuthor(int authorId) {
        AuthorModel authorModel = controller.getById(authorId);
        Factory.getInstance().end();
        return authorModel;
    }

    public AuthorModel updateAuthor(AuthorModel author) {
        return controller.update(author);
    }
}
