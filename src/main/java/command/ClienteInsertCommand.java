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
import model.Endereco;

/**
 *
 * @author michel
 */
public class ClienteInsertCommand extends Command {
    
    

    public ClienteInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        
        Endereco endereco = new Endereco();
        
        endereco.setEstado(request.queryParams("estado"));
        endereco.setCidade(request.queryParams("cidade"));
        endereco.setBairro(request.queryParams("bairro"));
        endereco.setRua(request.queryParams("rua"));
        endereco.setNumero(request.queryParams("numero"));
        endereco.setComplemento(request.queryParams("complemento"));
        new EnderecoDAO().insert(endereco);
        
        
        Cliente cliente = new Cliente();
        cliente.setNome(request.queryParams("nome"));
        cliente.setTelefone(request.queryParams("telefone"));
        cliente.setEmail(request.queryParams("email"));
        cliente.setCpf(request.queryParams("cpf"));
        cliente.setCnpj(request.queryParams("cnpj"));
        cliente.setFisico(Boolean.parseBoolean(request.queryParams("fisico")));
        cliente.setEndereco( new EnderecoDAO().ultimo());
        
        new ClienteDAO().insert(cliente);
        
        map.put("message", "Voce acaba de inserir o cliente com sucesso!");
    }    
    

}
