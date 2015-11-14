/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.FuncionarioDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Funcionario;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import database.EnderecoDAO;
import java.sql.SQLException;

/**
 *
 * @author michel
 */
public class FuncionarioInsertCommand extends Command {
    
    

    public FuncionarioInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.queryParams("nome"));
        funcionario.setTelefone(request.queryParams("telefone"));
        funcionario.setEmail(request.queryParams("email"));
        funcionario.setCpf(request.queryParams("cpf"));
        funcionario.setAtivo(Boolean.parseBoolean(request.queryParams("ativo")));
        funcionario.setEndereco(new EnderecoDAO().selectById(Integer.parseInt(request.queryParams("endereco"))));
        
        new FuncionarioDAO().insert(funcionario);
        
        map.put("message", "Voce acaba de inserir o funcionario com sucesso!");
    }    
    

}
