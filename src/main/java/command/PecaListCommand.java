package command;

import database.PecaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Peca;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class PecaListCommand extends Command {

    public PecaListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Peca> pecas;
        try {
            pecas = new PecaDAO().select();
            if (pecas.size()> 0) { 
                map.put("pecas", pecas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
