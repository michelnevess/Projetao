package command;

import database.EnderecoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Endereco;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class EnderecoListCommand extends Command {

    public EnderecoListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Endereco> enderecos;
        try {
            enderecos = new EnderecoDAO().select();
            if (enderecos.size()> 0) { 
                map.put("enderecos", enderecos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
