package command;

import database.EnderecoDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class EnderecoEditCommand extends Command {

    public EnderecoEditCommand(Request request, Response response) throws SQLException {
        super(request, response);
        Endereco endereco = new Endereco();       
        endereco.setEstado(request.queryParams("estado"));
        endereco.setCidade(request.queryParams("cidade")); 
        endereco.setBairro(request.queryParams("bairro")); 
        endereco.setRua(request.queryParams("rua")); 
        endereco.setNumero(request.queryParams("numero")); 
        endereco.setComplemento(request.queryParams("complemento")); 
        
        endereco.setId(Integer.parseInt(request.queryParams("id")));
        new EnderecoDAO().update(endereco);
            map.put("message", "Voce acaba de editar o Endereco");
        
    }

}
