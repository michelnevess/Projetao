package command;

import database.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //map.put("name", "Seja bem vindo!!!");
        ArrayList<Funcionario> funcionarios;
        try {
            funcionarios = new FuncionarioDAO().select();
            if (funcionarios.size()> 0) { 
                map.put("funcionarios", funcionarios);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
