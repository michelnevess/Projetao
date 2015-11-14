/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.ClienteDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Cliente;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import database.EnderecoDAO;
import java.sql.SQLException;

/**
 *
 * @author michel
 */
public class ClienteInsertCommand extends Command {
    
    

    public ClienteInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        
        Cliente cliente = new Cliente();
        cliente.setNome(request.queryParams("nome"));
        cliente.setTelefone(request.queryParams("telefone"));
        cliente.setEmail(request.queryParams("email"));
        cliente.setCpf(request.queryParams("cpf"));
        cliente.setCnpj(request.queryParams("cnpj"));
        cliente.setFisico(Boolean.parseBoolean(request.queryParams("fisico")));
        cliente.setAtivo(Boolean.parseBoolean(request.queryParams("ativo")));
        cliente.setEndereco(new EnderecoDAO().selectById(Integer.parseInt(request.queryParams("endereco"))));
        
        new ClienteDAO().insert(cliente);
        
        map.put("message", "Voce acaba de inserir o cliente com sucesso!");
    }    
    

}
