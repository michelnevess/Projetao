/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peca;
import model.Servico;
import model.Servico_peca;
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
            
            
            ArrayList<Integer> ids = new ArrayList();
            ArrayList<Veiculo> veiculo = new ArrayList();
            ArrayList<String> titulo = new ArrayList();
            ArrayList<Servico_peca> servico_pecas;
            ArrayList<Peca> peca = new ArrayList();
            servico_pecas = new Servico_pecaDAO().select(servico.getId());
            for(int i = 0; i < servico_pecas.size(); i++){
                peca.add(servico_pecas.get(i).getPeca());
            }
            Calendar cf = Calendar.getInstance();
            cf.setTime((java.util.Date) servico.getD_fim());
            
            Calendar ci = Calendar.getInstance();
            ci.setTime((java.util.Date) servico.getD_inicio());
            
            map.put("descricao", servico.getDescricao());
            map.put("dia1", ci.get(Calendar.DAY_OF_MONTH));
            map.put("dia2", cf.get(Calendar.DAY_OF_MONTH));
            map.put("mes1", ci.get(Calendar.MONTH));
            map.put("mes2", cf.get(Calendar.MONTH));
            map.put("ano1", ci.get(Calendar.YEAR));
            map.put("ano2", cf.get(Calendar.YEAR));
            map.put("valor", servico.getValor());
            map.put("funcionarios", new FuncionarioDAO().select());
            map.put("clientes", new ClienteDAO().select());
            map.put("veiculos", new VeiculoDAO().select());
            map.put("id", servico.getId());
            map.put("servico_pecas", servico_pecas);
            map.put("pecas", peca);

        } catch (SQLException ex) {
            Logger.getLogger(ServicoScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
