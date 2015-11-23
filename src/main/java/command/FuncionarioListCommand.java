package command;

import database.EnderecoDAO;
import database.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;

import model.Funcionario;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class FuncionarioListCommand extends Command {

    public FuncionarioListCommand(Request request, Response response) {
        super(request, response);
        ArrayList<Funcionario> funcionarios;
        ArrayList<Endereco> endereco = new ArrayList();
        try {
            funcionarios = new FuncionarioDAO().select();
            for (int i =0; i< funcionarios.size(); i++) {
                endereco.add(funcionarios.get(i).getEndereco());
            }
            if (funcionarios.size()> 0) { 
                map.put("funcionarios", funcionarios);
                
            }
            if (endereco.size()>0){
                map.put("enderecos", endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
