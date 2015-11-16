package command;

import database.PecaDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class PecaEditCommand extends Command {

    public PecaEditCommand(Request request, Response response) throws SQLException {
        super(request, response);
        Peca peca = new Peca();       
        peca.setNome(request.queryParams("nome"));
        peca.setFornecedor(request.queryParams("fornecedor"));
        peca.setValor(Double.parseDouble(request.queryParams("valor")));
        peca.setId(Integer.parseInt(request.queryParams("id")));
        new PecaDAO().update(peca);
            map.put("message", "Voce acaba de editar a Peca");
        
    }

}
