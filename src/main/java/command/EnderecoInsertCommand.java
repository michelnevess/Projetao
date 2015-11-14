/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.EnderecoDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Endereco;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class EnderecoInsertCommand extends Command {
    
    

    public EnderecoInsertCommand(Request request, Response response) {
        super(request, response);     
        
        
        Endereco endereco = new Endereco();
        endereco.setEstado(request.queryParams("estado"));
        endereco.setCidade(request.queryParams("cidade"));
        endereco.setBairro(request.queryParams("bairro"));
        endereco.setRua(request.queryParams("rua"));
        endereco.setNumero(request.queryParams("numero"));
        endereco.setComplemento(request.queryParams("complemento"));
        
        new EnderecoDAO().insert(endereco);
        
        map.put("message", "Voce acaba de inserir o endereco com sucesso!");
    }    
    

}
