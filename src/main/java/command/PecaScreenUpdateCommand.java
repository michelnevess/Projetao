/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.PecaDAO;
import database.EnderecoDAO;
import database.FuncionarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peca;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
    
    public class PecaScreenUpdateCommand extends Command {

    public PecaScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Peca peca;
         
         try {
            peca = new PecaDAO().selectById(Integer.parseInt(request.params(":id")));
            map.put("nome", peca.getNome());
            map.put("fornecedor", new FuncionarioDAO().select());
            map.put("valor", peca.getValor());
            map.put("id", peca.getId());

        } catch (SQLException ex) {
            Logger.getLogger(PecaScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
