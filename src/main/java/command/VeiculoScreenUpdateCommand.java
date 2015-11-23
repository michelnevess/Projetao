
package command;

import database.ClienteDAO;
import database.VeiculoDAO;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Veiculo;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class VeiculoScreenUpdateCommand extends Command {

    public VeiculoScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Veiculo veiculo;
         ArrayList<Cliente> clientes = null;
        try {
            veiculo = new VeiculoDAO().selectById(Integer.parseInt(request.params(":id")));
            // PROBLEMA
            map.put("modelo", veiculo.getModelo());
            map.put("marca", veiculo.getMarca());
            map.put("ano", veiculo.getAno());
            map.put("chassi", veiculo.getChassi());
            map.put("placa", veiculo.getPlaca());
            
            clientes = new ClienteDAO().select();
            if (clientes.size() > 0) {
                map.put("clientes", clientes);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
