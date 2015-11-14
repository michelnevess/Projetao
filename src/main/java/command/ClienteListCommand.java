package command;

import database.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cliente;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ClienteListCommand extends Command {

    public ClienteListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Cliente> clientes;
        try {
            clientes = new ClienteDAO().select();
            if (clientes.size()> 0) { 
                map.put("clientes", clientes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
