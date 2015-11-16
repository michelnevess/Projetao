/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.ClienteDAO;
import database.FuncionarioDAO;
import database.PecaDAO;
import database.ServicoDAO;
import database.Servico_pecaDAO;
import database.VeiculoDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Servico;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Peca;
import model.Servico_peca;

/**
 *
 * @author michel
 */
public class ServicoInsertCommand extends Command {
    
    

    public ServicoInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        
        Servico servico = new Servico();
        servico.setDescricao(request.queryParams("descricao"));
        servico.setD_inicio(Date.valueOf(request.queryParams("ano1")+":"+request.queryParams("mes1")+":"+request.queryParams("dia1")));
        servico.setD_fim(Date.valueOf(request.queryParams("ano2")+":"+request.queryParams("mes2")+":"+request.queryParams("dia2")));
        servico.setValor(Double.parseDouble(request.queryParams("valor")));
        servico.setCliente(new ClienteDAO().selectById(Integer.parseInt(request.queryParams("cliente_id"))));
        servico.setFuncionario(new FuncionarioDAO().selectById(Integer.parseInt(request.queryParams("funcionario_id"))));
        servico.setVeiculo(new VeiculoDAO().selectById(Integer.parseInt(request.queryParams("veiculo_id"))));
        
        new ServicoDAO().insert(servico);
        
        for (int i = 0; i < Integer.parseInt(request.queryParams("quantidade")); i++){
            Servico_peca sp = new Servico_peca();
            Peca peca = new PecaDAO().selectById(Integer.parseInt(request.queryParams("peca")));
        
            sp.setServico(new ServicoDAO().ultimo().getId());
            sp.setQuantidade(Integer.parseInt(request.queryParams("unidade")));
            sp.setPeca(peca);
            sp.setValor(peca.getValor());
            
            new Servico_pecaDAO().insert(sp);
        }
        map.put("message", "Voce acaba de inserir o servico com sucesso!");
    }    
    

}
