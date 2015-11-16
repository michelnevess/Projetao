package init;

import command.*;
import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import spark.SparkBase;
import spark.TemplateViewRoute;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

    public static void main(String[] args) {

        staticFileLocation("/html"); 
        
        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "index.html");
            }
        }, new MustacheTemplateEngine());
        
        
        get("/funcionario", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new FuncionarioListCommand(request, response).getMap(), "funcionario.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/cliente", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteListCommand(request, response).getMap(), "cliente.html");
            }
        }, new MustacheTemplateEngine());

        get("/endereco", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new EnderecoListCommand(request, response).getMap(), "endereco.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/historico", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new HistoricoListCommand(request, response).getMap(), "historico.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/peca", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new PecaListCommand(request, response).getMap(), "peca.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/servico", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ServicoListCommand(request, response).getMap(), "servico.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/gasto", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Servico_pecaListCommand(request, response).getMap(), "gasto.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/veiculo", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new VeiculoListCommand(request, response).getMap(), "veiculo.html");
            }
        }, new MustacheTemplateEngine());
        
        
        
        get("/clientedelete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteDeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/enderecodelete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new EnderecoDeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/funcionariodelete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new FuncionarioDeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/pecadelete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new PecaDeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/veiculodelete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new VeiculoDeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());

        // insert          
        get("/clientecadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "clientecadastro.html");
            }
        }, new MustacheTemplateEngine());

        post("/clienteinsert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ClienteInsertCommand(request, response).getMap(), "message.html");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        

    }
}
