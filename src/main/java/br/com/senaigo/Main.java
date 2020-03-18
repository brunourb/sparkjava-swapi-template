package br.com.senaigo;

import br.com.senaigo.controller.PeopleController;
import br.com.senaigo.model.People;
import br.com.senaigo.model.User;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static spark.Spark.*;

public class Main {

    final static String CONTENT_TYPE = "application/json";
    final static String TOKEN = "Bearer b9QLldmqZSVSsLfbubqR35SaTTzN8QVD";

    final static Logger log = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {
//        users();
        peoples();
    }

    private static boolean validaBearer(Request request){
        return TOKEN.equals(request.headers("Authorization"));
    }

    public static void peoples(){

        PeopleController controller = new PeopleController();

        //http://localhost:4567/api
        path("/api", () -> {
            before("/*", (request, response) -> {
                log.info("Received api call");
                if(validaBearer(request)){
                    log.info("Autorizado");
                    response.status(200);
                    response.body("Passou");
                }
                else{
                    log.info("Não autorizado");
                    response.status(401);
                    response.body("Campeao, senha errada");
                }
            });
            //path people
            path("/peoples",() ->{
                //CREATE
                post("",(request,response)->{

                    People people = new Gson().fromJson(request.body(),
                            People.class);

                    //Invocar controlador
                    People people1 = controller.create(people);
                    response.status(201);
                    response.body(new Gson().toJson(people1));

                    return response.body();
                }); //C. reate
                //READ
                get("",(request,response)->{

                    List<People> peopleList = controller.read();

                    if(peopleList!=null && peopleList.size()>=10){
                        //Content-Range, Content-Length
                        response.header("Content-Type","application/json");
//                        response.header("Content-Range","10");
//                        response.header("Content-Length",String.valueOf(peopleList.size()));
                        response.status(206);
                        response.body(new Gson().toJson(peopleList));
                    }
                    else if(peopleList.size()>0){
                        response.status(200);
                        response.body(new Gson().toJson(peopleList));
                    }
                    else{
                        response.status(204);
                    }

                    return response.body();
                }); // R. ead
                get("/:id",(request,response)->{

                    Integer id = Integer.parseInt(request.params(":id"));

                    People people = controller.read(id);

                    if(peopleIsNull(people)){
                        response.status(404);
                        response.body(new Gson().toJson(people));
                    }
                    else{
                        response.status(200);
                        response.body(new Gson().toJson(people));
                    }

                    return response.body();
                }); // R. ead
                //ATUALIZA TODO O REGISTRO (OBJETO)
                put("",(request,response)->{
                    People people = new Gson().fromJson(request.body(),
                            People.class);
                    People p1 = controller.updateAll(people);

                    response.status(204);
                    response.body(new Gson().toJson(p1));

                    return response.body();
                });
                //ATUALIZA PARTE DE UM OBJETO
                patch("", (request,response)->{
                    People people = new Gson().fromJson(request.body(),
                            People.class);
                    People p1 = controller.update(people);

                    response.status(204);
                    response.body(new Gson().toJson(p1));
                    return response.body();
                }); //altera parte do objeto
                delete("/:id",(request,response)->{

                    Integer id = Integer.parseInt(request.params(":id"));

                    controller.delete(id);
                    response.status(204);
                    return response.body();
                }); //deleta um registro.
            });

        });
    }

    private static boolean peopleIsNull(People people) {
        return people==null;
    }

    /**
     * 1. Sempre observar o status e os verbos da requiscao.
     * POST -> 201 (registro novo), 204 (sem retorno)
     * GET -> paginacao sempre (206), Content-Range, Content-Length
     * PUT -> 204 (no content)
     * DELETE -> 204 (no content)
     * OPTIONS -> listar verbos habilitados para o consumidor
     *
     * Nível 2 (status + verbos)
     * Trabalhar com rotas sugestivas ao recurso consumido.
     *
     * GET /api/people
     * Nao fazer isso:
     * api/peopleAtivas, api/peopleAtivas/cadastrar
     *
     * Versionamento:
     * api/v1
     * api/v2
     * ou versionar no content-Type enviado para o servidor;
     * application/people.1.0.0+json
     * applicaton/people.1.0.0+hal
     *
     * Não fazer versionamento com queryString:
     * api/people/version?v=1
     *
     *
     */
    public static void api(){
        get("/", (request, response) -> {
            // Show something
            return null;
        });

        post("/", (request, response) -> {
            // Create something
            return null;
        });

        put("/", (request, response) -> {
            // Update something
            return null;
        });

        delete("/", (request, response) -> {
            // Annihilate something
            return null;
        });

        options("/", (request, response) -> {
            // Appease something
            return null;
        });
    }

    public static void users(){

        List<User> lista = new ArrayList<User>();

        path("/api", () -> {
            before("/*", (request, response) ->  log.info("Received api call"));
            path("/user", () -> {

                get("", (request, response) -> {

                    //"TIPANDO" o retorno da requisição

                    response.type(CONTENT_TYPE);
                    response.status(200);
                    response.body(new Gson().toJson("texto qualquer"));

                    return response.body();
                });

                post("", (request, response) -> {

                    //"TIPANDO" o retorno da requisição

                    response.type(CONTENT_TYPE);
                    response.status(201);

                    return new Gson().toJson("texto qualquer");
                });
                put("", (request, response) -> {


                    //User user = new Gson().fromJson(request.body(), User.class);

                    response.status(201);
                    return new Gson().toJson("texto qualquer");
                });
                delete("/:id", (request, response) -> {

                    User user = new User();
                    //user.setId(Integer.parseInt(request.params(":id")));

                    response.status(204);
                    return response;
                });
            });
        });
    }



}
