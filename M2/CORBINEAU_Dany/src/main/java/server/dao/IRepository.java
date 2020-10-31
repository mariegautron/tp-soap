package server.dao;

import java.util.List;

public interface IRepository<T> {
    void add(Object object);
    void del(Object object);
    void update(Object object);
    T get(int id);
    List<T> getAll();
}
