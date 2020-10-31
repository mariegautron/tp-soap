package server.dao;

public class RepositoryManager {
    private RepositoryManager() {}

    public static <T> RepositoryImpl<T> getRepo(Class<T> classType)
    {
        RepositoryImpl<T> rep = new RepositoryImpl<T>();
        rep.classType = classType;
        return rep;
    }
}
