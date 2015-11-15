package command;

import database.VeiculoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Veiculo;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class VeiculoListCommand extends Command {

    public VeiculoListCommand(Request request, Response response) {
        super(request, response);
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Veiculo> veiculos;
        try {
            veiculos = new VeiculoDAO().select();
            if (veiculos.size()> 0) { 
                map.put("veiculos", veiculos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
