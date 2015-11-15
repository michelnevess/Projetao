package command;

import database.ServicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Servico;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ServicoListCommand extends Command {

    public ServicoListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Servico> servicos;
        try {
            servicos = new ServicoDAO().select();
            if (servicos.size()> 0) { 
                map.put("servicos", servicos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
