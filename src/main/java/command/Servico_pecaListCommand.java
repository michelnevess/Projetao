package command;

import database.ServicoDAO;
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
        ArrayList<Servico_peca> servico_pecas;
        try {
            servico_pecas = new Servico_pecaDAO().select(Integer.parseInt(request.params(":id")));
            double valor = new ServicoDAO().selectById(Integer.parseInt(request.params(":id"))).getValor();
            if (servico_pecas.size()> 0) { 
                map.put("servico_pecas", servico_pecas);
                map.put("mao", valor);
                map.put("total", new Servico_pecaDAO().total(Integer.parseInt(request.params(":id"))));
                map.put("pago", new ServicoDAO().taPago(Integer.parseInt(request.params(":id"))));
                map.put("id", Integer.parseInt(request.params(":id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servico_pecaListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
