package server.dao;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl<T> implements IRepository<T> {
    protected Class<T> classType = null;

    public void add(Object object) {
        Factory.getInstance().getSession().beginTransaction();
        Factory.getInstance().getSession().save(object);
        Factory.getInstance().getSession().flush();
        Factory.getInstance().getSession().getTransaction().commit();
    }

    public void del(Object object) {
        Factory.getInstance().getSession().beginTransaction();
        Factory.getInstance().getSession().delete(object);
        Factory.getInstance().getSession().flush();
        Factory.getInstance().getSession().getTransaction().commit();
    }

    public void update(Object object) {
        Factory.getInstance().getSession().beginTransaction();
        Factory.getInstance().getSession().saveOrUpdate(object);
        Factory.getInstance().getSession().flush();
        Factory.getInstance().getSession().getTransaction().commit();
    }

    public T get(int id) {
        return  classType.cast(Factory.getInstance().getSession().load(classType, id));
    }

    public List<T> getAll() {
        List<T> returnList = new ArrayList<T>();
        Query query = Factory.getInstance().getSession().createQuery("select O from "+classType.getSimpleName()+" as O");
        for (Object object : query.list()) {
            returnList.add(classType.cast(object));
        }
        return returnList;
    }
}
