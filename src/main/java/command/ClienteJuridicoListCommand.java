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
import model.Endereco;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ClienteJuridicoListCommand extends Command {

    public ClienteJuridicoListCommand(Request request, Response response) {
        super(request, response);
        ArrayList<Cliente> clientes;
        ArrayList<Endereco> endereco = new ArrayList();
       try {
            clientes = new ClienteDAO().select();
            ArrayList<Cliente> clientej = new ArrayList();
            for(int i = 0; i < clientes.size(); i++){
                if(!clientes.get(i).isFisico()){
                clientej.add(clientes.get(i));
                endereco.add(clientes.get(i).getEndereco());
                }
            }
                
            if (clientej.size()> 0) { 
                map.put("clientes", clientej);
            }
            if (endereco.size()>0){
                map.put("enderecos", endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJuridicoListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
