package command;

import database.Servico_pecaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Servico_peca;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class Servico_pecaListCommand extends Command {

    public Servico_pecaListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Servico_peca> servico_pecas;
        try {
            servico_pecas = new Servico_pecaDAO().select(Integer.parseInt(request.params(":id")));
            if (servico_pecas.size()> 0) { 
                map.put("servico_pecas", servico_pecas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servico_pecaListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
