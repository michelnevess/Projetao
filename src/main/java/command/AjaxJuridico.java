/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import spark.Request;
import spark.Response;

public class AjaxJuridico extends Command {

    private String resposta;
    private String respostaXML;

    public AjaxJuridico(Request request, Response response) {
        super(request, response);

        ArrayList<Cliente> clientes;
        try {
            clientes = new ClienteDAO().select();
            for (int i = 0; i < clientes.size(); i++) {
                if (!clientes.get(i).isFisico()) {
                    this.resposta += "|" + clientes.get(i).toString();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFisicoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getResposta() {
        return this.resposta;
    }

}
