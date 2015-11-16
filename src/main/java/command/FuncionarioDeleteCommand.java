/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.FuncionarioDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class FuncionarioDeleteCommand extends Command {

    public FuncionarioDeleteCommand(Request request, Response response) {
        super(request, response);
        try {
            new FuncionarioDAO().delete(Integer.parseInt(request.params(":id")));
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.redirect("/");
    }

}
