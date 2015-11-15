package command;

import database.EnderecoDAO;
import database.ClienteDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ClienteEditCommand extends Command {

    public ClienteEditCommand(Request request, Response response) throws SQLException {
        super(request, response);
        Cliente cliente = new Cliente();       
        cliente.setNome(request.queryParams("nome"));
        cliente.setTelefone(request.queryParams("telefone")); 
        cliente.setEmail(request.queryParams("email")); 
        cliente.setCpf(request.queryParams("cpf"));
        cliente.setCnpj(request.queryParams("cnpj"));
        cliente.setFisico(Boolean.parseBoolean(request.queryParams("fisico")));
        cliente.setEndereco(new EnderecoDAO().selectById(Integer.parseInt(request.queryParams("endereco"))));
        
        cliente.setId(Integer.parseInt(request.queryParams("id")));
        new ClienteDAO().update(cliente);
            map.put("message", "Voce acaba de editar o Cliente");
        
    }

}
