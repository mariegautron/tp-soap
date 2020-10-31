package server.dao;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {
    private static Factory instance;
    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    private final SessionFactory factory;
    private Session session;

    private Factory(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    public void end()
    {
        if(session != null)
            session.close();
        session = null;
    }

    public Session getSession()
    {
        if(session == null) {
            session = factory.openSession();
        }
        return session;
    }

}
