package command;

import database.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cliente;
import model.Endereco;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ClienteFisicoListCommand extends Command {

    public ClienteFisicoListCommand(Request request, Response response) {
        super(request, response);
        ArrayList<Cliente> clientes;
        ArrayList<Endereco> endereco = new ArrayList();
        
        try {
            clientes = new ClienteDAO().select();
            ArrayList<Cliente> clientef = new ArrayList();
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).isFisico()) {
                    clientef.add(clientes.get(i));
                    endereco.add(clientes.get(i).getEndereco());
                }
            }
            if (clientes.size() > 0) {
                map.put("clientes", clientef);
            }
            if (endereco.size()>0){
                map.put("enderecos", endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFisicoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
