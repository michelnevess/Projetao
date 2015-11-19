/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import database.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.ClienteList;
import spark.Request;
import spark.Response;

public class AjaxFisico extends Command {

    private String resposta;
    private final String respostaXML;

    public AjaxFisico(Request request, Response response) throws SQLException {
        super(request, response);
        XStream xstream = new XStream();
        xstream.alias("cliente", Cliente.class);
        xstream.alias("clientes", ClienteList.class);
        xstream.addImplicitCollection(ClienteList.class, "list");

        ClienteList list = new ClienteList();
        list.setList(new ClienteDAO().select());
       
        this.respostaXML = xstream.toXML(list);

    }

    public String getResposta() {
        return this.resposta;
    }

    public String getRepostaXML() {
        return this.respostaXML;
    }

}
