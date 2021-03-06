/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ServicoDAO;
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
public class PagarServicoCommand extends Command {

    public PagarServicoCommand(Request request, Response response) {
        super(request, response);
        try {
            new ServicoDAO().pago(Integer.parseInt(request.params(":id")));
        } catch (SQLException ex) {
            Logger.getLogger(PagarServicoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.redirect("/servico_peca/"+Integer.parseInt(request.params(":id")));
    }

}
