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
import model.Endereco;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ClienteEditCommand extends Command {

    public ClienteEditCommand(Request request, Response response) throws SQLException {
        super(request, response);
        
        Endereco endereco = new Endereco();
        
        endereco.setEstado(request.queryParams("estado"));
        endereco.setCidade(request.queryParams("cidade"));
        endereco.setBairro(request.queryParams("bairro"));
        endereco.setRua(request.queryParams("rua"));
        endereco.setNumero(request.queryParams("numero"));
        endereco.setComplemento(request.queryParams("complemento"));
        endereco.setId(Integer.parseInt(request.queryParams("id2")));
        new EnderecoDAO().update(endereco);
        
        
        Cliente cliente = new Cliente();
        cliente.setNome(request.queryParams("nome"));
        cliente.setTelefone(request.queryParams("telefone"));
        cliente.setEmail(request.queryParams("email"));
        cliente.setCpf(request.queryParams("cpf"));
        cliente.setCnpj(request.queryParams("cnpj"));
        cliente.setFisico(Boolean.parseBoolean(request.queryParams("fisico")));
        cliente.setEndereco(endereco);
        cliente.setId(Integer.parseInt(request.queryParams("id2")));
        new ClienteDAO().update(cliente);
            response.redirect("/cliente");
        
    }

}
