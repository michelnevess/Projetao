
package command;

import database.VeiculoDAO;
import java.awt.Color;
import java.sql.SQLException;
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
public class VeiculoScreenUpdateCommand extends Command {

    public VeiculoScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Veiculo veiculo;
        try {
            veiculo = new VeiculoDAO().selectById(Integer.parseInt(request.params(":id")));
            // PROBLEMA
            map.put("modelo", veiculo.getModelo());
            map.put("marca", veiculo.getMarca());
            map.put("ano", veiculo.getAno());
            map.put("chassi", veiculo.getChassi());
            map.put("placa", veiculo.getPlaca());
            map.put("veiculo", new VeiculoDAO().select());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
