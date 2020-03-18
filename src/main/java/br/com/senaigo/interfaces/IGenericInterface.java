package br.com.senaigo.interfaces;

import java.util.List;

public interface IGenericInterface<T,N> {

    T create(T entity);

    T read(N id);

    List<T> read();

    T update(T t);

    T updateAll(T t);

    void delete(N id);
}
