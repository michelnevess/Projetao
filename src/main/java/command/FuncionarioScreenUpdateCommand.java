/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.FuncionarioDAO;
import database.EnderecoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Funcionario;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
    
    public class FuncionarioScreenUpdateCommand extends Command {

    public FuncionarioScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Funcionario funcionario;
         
         try {
            funcionario = new FuncionarioDAO().selectById(Integer.parseInt(request.params(":id")));
            map.put("nome", funcionario.getNome());
            map.put("telefone", funcionario.getTelefone());
            map.put("email", funcionario.getEmail());
            map.put("cpf", funcionario.getCpf());
            
            map.put("estado", funcionario.getEndereco().getEstado());
            map.put("cidade", funcionario.getEndereco().getCidade());
            map.put("bairro", funcionario.getEndereco().getBairro());
            map.put("rua", funcionario.getEndereco().getRua());
            map.put("numero", funcionario.getEndereco().getNumero());
            map.put("complemento", funcionario.getEndereco().getComplemento());
            
            map.put("id", funcionario.getId());
            map.put("id2", funcionario.getEndereco().getId());
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
