package br.com.senaigo.interfaces;

import spark.Response;

import java.util.List;

public interface IController<T,N> {

    T create(T entity);

    T read(N id);

    List<T> read();

    T update(T t);

    T updateAll(T t);

    void delete(N id);
}
