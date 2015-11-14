/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.PecaDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Peca;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class PecaInsertCommand extends Command {
    
    

    public PecaInsertCommand(Request request, Response response) {
        super(request, response);     
        
        
        Peca peca = new Peca();
        peca.setNome(request.queryParams("nome"));
        peca.setFornecedor(request.queryParams("fornecedor"));
        peca.setValor(Double.parseDouble(request.queryParams("valor")));
        
        new PecaDAO().insert(peca);
        
        map.put("message", "Voce acaba de inserir o peca com sucesso!");
    }    
    

}
