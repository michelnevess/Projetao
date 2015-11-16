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
         ArrayList<Funcionario> funcionario;
         funcionario = new FuncionarioDAO().select();
         
        for (Funcionario funcionario1 : funcionario) {
            map.put("funcionario", funcionario1.getNome());
            map.put("funcionario_id", funcionario1.getId());
        }
        
        ArrayList<Cliente> cliente;
         cliente = new ClienteDAO().select();
         
        for (Cliente cliente1 : cliente) {
            map.put("cliente", cliente1.getNome());
            map.put("cliente_id", cliente1.getId());
        }
        
        ArrayList<Veiculo> veiculo;
         veiculo = new VeiculoDAO().select();
         
        for (Veiculo veiculo1 : veiculo) {
            map.put("veiculo", veiculo1.toString());
            map.put("veiculo_id", veiculo1.getId());
        }
        
    }
    
}
