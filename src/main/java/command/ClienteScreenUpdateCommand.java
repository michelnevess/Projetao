/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClienteDAO;
import database.EnderecoDAO;
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
    
    public class ClienteScreenUpdateCommand extends Command {

    public ClienteScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Cliente cliente;
         
         try {
            cliente = new ClienteDAO().selectById(Integer.parseInt(request.params(":id")));
            map.put("nome", cliente.getNome());
            map.put("telefone", cliente.getTelefone());
            map.put("email", cliente.getEmail());
            map.put("cpf", cliente.getCpf());
            map.put("cnpj", cliente.getCnpj());
            
            
            map.put("estado", cliente.getEndereco().getEstado());
            map.put("cidade", cliente.getEndereco().getCidade());
            map.put("bairro", cliente.getEndereco().getBairro());
            map.put("rua", cliente.getEndereco().getRua());
            map.put("numero", cliente.getEndereco().getNumero());
            map.put("complemento", cliente.getEndereco().getComplemento());
            
            map.put("id", cliente.getId());
            map.put("id2", cliente.getEndereco().getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
