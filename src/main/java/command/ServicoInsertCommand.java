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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import model.Peca;
import model.Servico_peca;

/**
 *
 * @author michel
 */
public class ServicoInsertCommand extends Command {
    
    

    public ServicoInsertCommand(Request request, Response response) throws SQLException, ParseException {
        super(request, response);     
        
        
        Servico servico = new Servico();
        
        Calendar c1 = Calendar.getInstance(); 
        c1.set(Integer.parseInt(request.queryParams("ano1")),Integer.parseInt(request.queryParams("mes1")) -1, Integer.parseInt(request.queryParams("dia1"))); 
        java.util.Date d1 = c1.getTime();
        
        Calendar c2 = Calendar.getInstance(); 
        c2.set(Integer.parseInt(request.queryParams("ano2")), Integer.parseInt(request.queryParams("mes2")) -1, Integer.parseInt(request.queryParams("dia2")));
        java.util.Date d2 = c2.getTime();
        
        servico.setD_inicio(d1);
        servico.setD_fim(d2);
        servico.setValor(Double.parseDouble(request.queryParams("valor")));
        servico.setCliente(new ClienteDAO().selectById(Integer.parseInt(request.queryParams("cliente"))));
        servico.setFuncionario(new FuncionarioDAO().selectById(Integer.parseInt(request.queryParams("funcionario"))));
        servico.setVeiculo(new VeiculoDAO().selectById(Integer.parseInt(request.queryParams("veiculo"))));
        servico.setDescricao(request.queryParams("descricao"));
        
        new ServicoDAO().insert(servico);
        
        
        Servico_peca sp = new Servico_peca();
        Peca peca = new PecaDAO().selectById(Integer.parseInt(request.queryParams("peca")));
        sp.setServico(new ServicoDAO().ultimo().getId());
            sp.setQuantidade(Integer.parseInt(request.queryParams("unidade")));
            sp.setPeca(peca);
            sp.setValor(peca.getValor());
            new Servico_pecaDAO().insert(sp);
            
        response.redirect("/");
    }    
    

}
