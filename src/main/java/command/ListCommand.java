/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClassificadoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Classificado;
import spark.Request;
import spark.Response;

/**
 *
 * @author iapereira
 */
public class ListCommand extends Command {

    public ListCommand(Request request, Response response) {
        super(request, response);
        map.put("name", "Seja bem vindo!!!");
        ArrayList<Classificado> classificados;
        try {
            classificados = new ClassificadoDAO().select();
            if (classificados.size()> 0) { 
                map.put("classificados", classificados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
