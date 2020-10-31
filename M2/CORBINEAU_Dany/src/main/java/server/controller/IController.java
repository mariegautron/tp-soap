package server.controller;

import java.util.List;

public interface IController<T> {
    T getById(int id);
    T[] getAll();
    T update(T object) throws NotImplementedException;
    T delete(int id);
    T add(T object);
}
