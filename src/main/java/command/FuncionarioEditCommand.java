package command;

import database.EnderecoDAO;
import database.FuncionarioDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Funcionario funcionario = new Funcionario();       
        funcionario.setNome(request.queryParams("nome"));
        funcionario.setTelefone(request.queryParams("telefone")); 
        funcionario.setEmail(request.queryParams("email")); 
        funcionario.setCpf(request.queryParams("cpf")); 
        funcionario.setEndereco(new EnderecoDAO().selectById(Integer.parseInt(request.queryParams("endereco"))));
        
        funcionario.setId(Integer.parseInt(request.queryParams("id")));
        new FuncionarioDAO().update(funcionario);
            map.put("message", "Voce acaba de editar o Funcionario");
        
    }

}
