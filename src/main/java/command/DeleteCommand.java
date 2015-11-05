/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClassificadoDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class DeleteCommand extends Command {

    public DeleteCommand(Request request, Response response) {
        super(request, response);
        try {
            new ClassificadoDAO().delete(Integer.parseInt(request.params(":id")));
        } catch (SQLException ex) {
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.redirect("/");
    }

}
