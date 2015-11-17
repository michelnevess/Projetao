/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ClienteDAO;
import database.ServicoDAO;
import database.EnderecoDAO;
import database.FuncionarioDAO;
import database.VeiculoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Servico;
import model.Veiculo;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
    
    public class ServicoScreenUpdateCommand extends Command {

    public ServicoScreenUpdateCommand(Request request, Response response) {
        super(request, response);
         Servico servico;
         
         try {
            servico = new ServicoDAO().selectById(Integer.parseInt(request.params(":id")));
            Calendar d_inicio = Calendar.getInstance();
            d_inicio.setTime(servico.getD_inicio());
            Calendar d_fim = Calendar.getInstance();
            d_fim.setTime(servico.getD_fim());
            
            ArrayList<Integer> ids = new ArrayList();
            ArrayList<Veiculo> veiculo = new ArrayList();
            ArrayList<String> titulo = new ArrayList();
            
            for(int i = 0; i < veiculo.size(); i++){
                ids.add(veiculo.get(i).getId());
                titulo.add(veiculo.get(i).toString());
            }
            
            
            map.put("descricao", servico.getDescricao());
            map.put("dia1", d_inicio.get(Calendar.DAY_OF_MONTH));
            map.put("dia2", d_fim.get(Calendar.DAY_OF_MONTH));
            map.put("mes1", d_inicio.get(Calendar.MONTH));
            map.put("mes2", d_fim.get(Calendar.MONTH));
            map.put("ano1", d_inicio.get(Calendar.YEAR));
            map.put("ano2", d_fim.get(Calendar.YEAR));
            map.put("valor", servico.getValor());
            map.put("funcionario", new FuncionarioDAO().select());
            map.put("cliente", new ClienteDAO().select());
            map.put("veiculo", new VeiculoDAO().select());
            map.put("id", servico.getId());

        } catch (SQLException ex) {
            Logger.getLogger(ServicoScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
