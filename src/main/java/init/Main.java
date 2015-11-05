package init;

import command.DeleteMultipleCommand;
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

/**
 * *
 * Classe que determina as rotas
 *
 * @author iapereira
 */
public class Main {

    public static void main(String[] args) {

        staticFileLocation("/html"); 
        
        // index
        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new ListCommand(request, response).getMap(), "index.html");
            }
        }, new MustacheTemplateEngine());

        // delete
        get("/delete/:id", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new DeleteCommand(request, response), "");
            }
        }, new MustacheTemplateEngine());
        
        post("/delete_multiple", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                try {
                    return new ModelAndView(new DeleteMultipleCommand(request, response).getMap(), "message.html");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }, new MustacheTemplateEngine());

        // insert          
        get("/screen_insert", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(new Command(request, response), "screen_insert.html");
            }
        }, new MustacheTemplateEngine());

        post("/insert/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                
                return new ModelAndView(new InsertCommand(request, response).getMap(), "message.html");
            }
        }, new MustacheTemplateEngine());
        

    }
}
