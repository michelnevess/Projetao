/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ServicoScreenInsertCommand extends Command {
    
    public ServicoScreenInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);
         
            map.put("clientes", new ClienteDAO().select());
            map.put("funcionarios", new FuncionarioDAO().select());
            map.put("veiculos", new VeiculoDAO().select());
            map.put("pecas", new PecaDAO().select());
        
    }
    
}
