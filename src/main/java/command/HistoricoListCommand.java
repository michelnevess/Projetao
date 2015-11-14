package command;

import database.HistoricoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Historico;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class HistoricoListCommand extends Command {

    public HistoricoListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Historico> historicos;
        try {
            historicos = new HistoricoDAO().select();
            if (historicos.size()> 0) { 
                map.put("historicos", historicos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
