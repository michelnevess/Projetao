package command;

import database.ClienteDAO;
import database.VeiculoDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class VeiculoEditCommand extends Command {

    public VeiculoEditCommand(Request request, Response response) throws SQLException {
        super(request, response);
        Veiculo veiculo = new Veiculo();       
        veiculo.setModelo(request.queryParams("modelo"));
        veiculo.setMarca(request.queryParams("marca")); 
        veiculo.setPlaca(request.queryParams("placa")); 
        veiculo.setChassi(request.queryParams("chassi")); 
        veiculo.setCliente(new ClienteDAO().selectById(Integer.parseInt(request.queryParams("cliente_is"))));
        
        veiculo.setId(Integer.parseInt(request.queryParams("id")));
        new VeiculoDAO().update(veiculo);
            map.put("message", "Voce acaba de editar o Veiculo");
        
    }

}
