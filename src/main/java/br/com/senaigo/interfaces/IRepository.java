package br.com.senaigo.interfaces;

import java.util.List;

public interface IRepository<T, N> {

    T create(T entity);

    List<T> read();

    T read(N id);

    T update(T entity); //patch

    T updateAll(T entity); //put

    void delete(N id);
}
