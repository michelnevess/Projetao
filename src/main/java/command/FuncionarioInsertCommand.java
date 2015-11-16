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
import model.Endereco;

/**
 *
 * @author michel
 */
public class FuncionarioInsertCommand extends Command {
    
    

    public FuncionarioInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        Endereco endereco = new Endereco();
        
        endereco.setEstado(request.queryParams("estado"));
        endereco.setCidade(request.queryParams("cidade"));
        endereco.setBairro(request.queryParams("bairro"));
        endereco.setRua(request.queryParams("rua"));
        endereco.setNumero(request.queryParams("numero"));
        endereco.setComplemento(request.queryParams("complemento"));
        new EnderecoDAO().insert(endereco);
        
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.queryParams("nome"));
        funcionario.setTelefone(request.queryParams("telefone"));
        funcionario.setEmail(request.queryParams("email"));
        funcionario.setCpf(request.queryParams("cpf"));
        funcionario.setEndereco(new EnderecoDAO().ultimo());
        
        new FuncionarioDAO().insert(funcionario);
        
        map.put("message", "Voce acaba de inserir o funcionario com sucesso!");
    }    
    

}
