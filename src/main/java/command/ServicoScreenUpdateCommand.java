/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import database.ServicoDAO;
import database.EnderecoDAO;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Servico;
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
            
            map.put("descricao", servico.getDescricao());
            map.put("dia1", d_inicio.get(Calendar.DAY_OF_MONTH));
            map.put("dia2", d_fim.get(Calendar.DAY_OF_MONTH));
            map.put("mes1", d_inicio.get(Calendar.MONTH));
            map.put("mes2", d_fim.get(Calendar.MONTH));
            map.put("ano1", d_inicio.get(Calendar.YEAR));
            map.put("ano2", d_fim.get(Calendar.YEAR));
            map.put("valor", servico.getValor());
            map.put("funcionario", servico.getFuncionario().getNome());
            map.put("funcionario_id", servico.getFuncionario().getId());
            map.put("cliente", servico.getCliente().getNome());
            map.put("cliente_id", servico.getCliente().getId());
            map.put("veiculo", servico.getVeiculo().toString());
            map.put("veiculo_id", servico.getVeiculo().getId());
            map.put("id", servico.getId());

        } catch (SQLException ex) {
            Logger.getLogger(ServicoScreenUpdateCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
