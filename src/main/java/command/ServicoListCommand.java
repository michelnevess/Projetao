package command;

import database.ServicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Funcionario;

import model.Servico;
import model.Veiculo;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ServicoListCommand extends Command {

    public ServicoListCommand(Request request, Response response) {
        super(request, response);
        ArrayList<Servico> servicos;
        ArrayList<Cliente> clientes = new ArrayList();
        ArrayList<Funcionario> funcionarios = new ArrayList();
        ArrayList<Veiculo> veiculos = new ArrayList();
        try {
            servicos = new ServicoDAO().select();
            if (servicos.size()> 0) { 
                map.put("servicos", servicos);
            }
            for (int i =0; i< servicos.size(); i++){
                clientes.add(servicos.get(i).getCliente());
                funcionarios.add(servicos.get(i).getFuncionario());
                veiculos.add(servicos.get(i).getVeiculo());
            }
            if(clientes.size()>0){
                map.put("clientes", clientes);
            }
            if(funcionarios.size()>0){
                map.put("funcionarios", funcionarios);
            }
            if(veiculos.size()>0){
                map.put("veiculos", veiculos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
