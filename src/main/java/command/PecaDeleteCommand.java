/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.PecaDAO;
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
public class PecaDeleteCommand extends Command {

    public PecaDeleteCommand(Request request, Response response) {
        super(request, response);
        try {
            new PecaDAO().delete(Integer.parseInt(request.params(":id")));
        } catch (SQLException ex) {
            Logger.getLogger(PecaListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.redirect("/");
    }

}
