package br.com.senaigo.service;

import br.com.senaigo.interfaces.IService;
import br.com.senaigo.model.People;
import br.com.senaigo.repository.PeopleRepository;

import java.util.List;
import java.util.logging.Logger;

public class PeopleService implements IService<People, Integer> {

    final static Logger log = Logger.getLogger(PeopleService.class.getName());

    //Injetar camada de repository
    private PeopleRepository repository = new PeopleRepository();

    @Override
    public People create(People entity) {
        log.info("Criado objeto people na camada de serviço");
        return repository.create(entity);
    }

    @Override
    public List<People> read() {

        List<People> lista = repository.read();
        log.info("Consulta realizada na camada de serviço.");
        log.info(String.format("%d registros consultados",lista.size()));

        return lista;
    }

    @Override
    public People read(Integer id) {
        log.info(String.format("People com identificador %d informado",id));
        return repository.read(id);
    }

    @Override
    public People update(People entity) {
        log.info("Atualizado registro");
        return repository.update(entity);
    }

    @Override
    public People updateAll(People entity) {
        log.info("Atualizado todo o registro");
        return repository.updateAll(entity);
    }

    @Override
    public void delete(Integer id) {
        log.info(String.format("Registro com identificador %d deletado.",id));
        repository.delete(id);

    }
}
