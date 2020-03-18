package br.com.senaigo.repository;

import br.com.senaigo.interfaces.IRepository;
import br.com.senaigo.model.People;
import br.com.senaigo.service.PeopleService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PeopleRepository implements IRepository<People, Integer> {

    final static Logger log = Logger.getLogger(PeopleRepository.class.getName());

    @Override
    public People create(People entity) {

        log.info("Registro persistido.");

        return entity;
    }

    @Override
    public List<People> read() {

        log.info("Retornado X registros da camada de repository");

        List<People> peopleList = new ArrayList<>();
        for(int i=0;i<10;i++){
            peopleList.add(People.builder()
                    .name("Nome"+i)
                    .created("created "+i)
                    .birthYear("2000/02/01")
                    .gender("male")
                    .homeWorld("Earth").build());
        }


        return peopleList;
    }

    @Override
    public People read(Integer id) {
        log.info(String.format("Registro com identificar %d informado",id));
        return People.builder().name("Luke").build();
    }

    @Override
    public People update(People entity) {
        log.info("Registro atualizado.");;
        return People.builder().name("Luke").build();
    }

    @Override
    public People updateAll(People entity) {
        log.info("RegistroALL atualizado");

        return People.builder().name("Luke").build();
    }

    @Override
    public void delete(Integer id) {
        log.info(String.format("Registro com o identificador %d deletado.",id));
    }
}
