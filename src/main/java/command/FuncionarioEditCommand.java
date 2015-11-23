package command;

import database.EnderecoDAO;
import database.FuncionarioDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;
import model.Funcionario;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class FuncionarioEditCommand extends Command {

    public FuncionarioEditCommand(Request request, Response response) throws SQLException {
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
        
        Funcionario funcionario = new Funcionario();       
        funcionario.setNome(request.queryParams("nome"));
        funcionario.setTelefone(request.queryParams("telefone")); 
        funcionario.setEmail(request.queryParams("email")); 
        funcionario.setCpf(request.queryParams("cpf")); 
        funcionario.setEndereco(endereco);
        
        funcionario.setId(Integer.parseInt(request.queryParams("id")));
        new FuncionarioDAO().update(funcionario);
            
        
    }

}
