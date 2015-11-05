/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.ClassificadoDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Classificado;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class InsertCommand extends Command {
    
    

    public InsertCommand(Request request, Response response) {
        super(request, response);     
        
        
        Classificado classificado = new Classificado();
        classificado.setContato(request.queryParams("contato"));
        classificado.setDescricao(request.queryParams("descricao"));
        
        new ClassificadoDAO().insert(classificado);
        
        map.put("message", "Voce acaba de inserir a classificado: " + request.queryParams("descricao") + " " + request.queryParams("contato"));
    }    
    

}
