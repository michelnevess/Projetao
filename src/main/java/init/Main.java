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
        
        
        get("/filtro1/:valor", (req, res) -> new Filtro1(req, res).getResposta());
        get("/filtro2/:valor", (req, res) -> new Filtro2(req, res).getResposta());
        
        
        
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
                return new ModelAndView(null, "cliente.html");
            }
        }, new MustacheTemplateEngine());

        get("/clientefisico", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteFisicoListCommand(request, response).getMap(), "clientefisico.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/clientejuridico", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteJuridicoListCommand(request, response).getMap(), "clientejuridico.html");
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
        
        get("/servico_peca/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Servico_pecaListCommand(request, response).getMap(), "servico_peca.html");
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
                return new ModelAndView(new ClienteDeleteCommand(request, response), "cliente.html");
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

                  
        get("/clientefisicocadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "clientefisicocadastro.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/clientejuridicocadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "clientejuridicocadastro.html");
            }
        }, new MustacheTemplateEngine());

        post("/clienteinsert/:tipo", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ClienteInsertCommand(request, response).getMap(), "/");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        post("/enderecoinsert", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "enderecocadastro.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/funcionariocadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "funcionariocadastro.html");
            }
        }, new MustacheTemplateEngine());

        post("/funcionarioinsert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new FuncionarioInsertCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());

        get("/pecacadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "pecacadastro.html");
            }
        }, new MustacheTemplateEngine());

        post("/pecainsert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new PecaInsertCommand(request, response).getMap(), "");
            }
        }, new MustacheTemplateEngine());
        
        get("/servicocadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ServicoScreenInsertCommand(request, response).getMap(), "servicocadastro.html");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());

        post("/servicoinsert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ServicoInsertCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/veiculocadastro", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new VeiculoScreenInsertCommand(request, response).getMap(), "veiculocadastro.html");
                
            }
        }, new MustacheTemplateEngine());

        post("/veiculoinsert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new VeiculoInsertCommand(request, response), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/clientefisicoupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteScreenUpdateCommand(request, response).getMap(), "clientefisicoupdate.html");
            }
        }, new MustacheTemplateEngine());
        
        get("/clientejuridicoupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ClienteScreenUpdateCommand(request, response).getMap(), "clientejuridicoupdate.html");
            }
        }, new MustacheTemplateEngine());

        post("/clienteedit/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ClienteEditCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/funcionarioupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new FuncionarioScreenUpdateCommand(request, response).getMap(), "funcionarioupdate.html");
            }
        }, new MustacheTemplateEngine());

        post("/funcionarioedit/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new FuncionarioEditCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/pecaupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new PecaScreenUpdateCommand(request, response).getMap(), "pecaupdate.html");
            }
        }, new MustacheTemplateEngine());

        post("/pecaedit/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new PecaEditCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/servicoupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ServicoScreenUpdateCommand(request, response).getMap(), "servicoupdate.html");
            }
        }, new MustacheTemplateEngine());

        post("/servicoedit/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new ServicoEditCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/veiculoupdate/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new VeiculoScreenUpdateCommand(request, response).getMap(), "veiculoupdate.html");
            }
        }, new MustacheTemplateEngine());

        post("/veiculoedit/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new VeiculoEditCommand(request, response).getMap(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());
        
        get("/pagar/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new PagarServicoCommand(request, response).getMap(), "");
            }
        }, new MustacheTemplateEngine());
        
        
    }
}
