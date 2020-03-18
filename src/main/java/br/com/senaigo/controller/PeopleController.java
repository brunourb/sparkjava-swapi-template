package br.com.senaigo.controller;

import br.com.senaigo.interfaces.IController;
import br.com.senaigo.model.People;
import br.com.senaigo.service.PeopleService;
import spark.Response;

import java.util.List;

public class PeopleController implements IController<People, Integer> {

    //Injetar camada de serviço
    private PeopleService service = new PeopleService();

    @Override
    public People create(People entity) {
        return service.create(entity);
    }

    @Override
    public People read(Integer id) {
        return service.read(id);
    }

    @Override
    public List<People> read() {
        return service.read();
    }

    @Override
    public People update(People people) {
        return service.update(people);
    }

    @Override
    public People updateAll(People people) {
        return service.updateAll(people);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }
}
