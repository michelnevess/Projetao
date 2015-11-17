/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class VeiculoScreenInsertCommand extends Command {
    
    public VeiculoScreenInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);
            map.put("cliente", new ClienteDAO().select());
    }
    
}