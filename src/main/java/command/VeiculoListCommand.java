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
        
        ArrayList<Veiculo> veiculos;
        ArrayList<String> nome = new ArrayList();
        try {
            veiculos = new VeiculoDAO().select();
            
            for(int i = 0; i < veiculos.size(); i++){
                nome.add(veiculos.get(i).getCliente().getNome());
            }
            
            if (veiculos.size()> 0) { 
                map.put("veiculos", veiculos);
                map.put("nome", nome);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
