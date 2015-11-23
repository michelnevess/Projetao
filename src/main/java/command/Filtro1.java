/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import database.ClienteDAO;
import database.ServicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Servico;
import spark.Request;
import spark.Response;
import org.json.*;

public class Filtro1 extends Command {

    private String resposta;
    

    public Filtro1(Request request, Response response) throws SQLException {
        super(request, response);
        
        resposta = new JSONArray(new ServicoDAO().filtro(request.params(":valor"))).toString();
        

    }

    public String getResposta() {
        return this.resposta;
    }

}
