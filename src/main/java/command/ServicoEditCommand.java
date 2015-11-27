package command;

import database.ClienteDAO;
import database.FuncionarioDAO;
import database.PecaDAO;
import database.ServicoDAO;
import database.Servico_pecaDAO;
import database.VeiculoDAO;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peca;
import model.Servico;
import model.Servico_peca;
import spark.Request;
import spark.Response;

/**
 *
 * @author michel
 */
public class ServicoEditCommand extends Command {

    public ServicoEditCommand(Request request, Response response) throws SQLException {
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
        
        
        servico.setId(Integer.parseInt(request.queryParams("id")));
        
        new ServicoDAO().update(servico);
        Servico_peca sp = new Servico_peca();
        Peca peca = new PecaDAO().selectById(Integer.parseInt(request.queryParams("peca")));
        sp.setServico(servico.getId());
            sp.setQuantidade(Integer.parseInt(request.queryParams("unidade")));
            sp.setPeca(peca);
            sp.setValor(peca.getValor());
            new Servico_pecaDAO().update(sp);        
        
            response.redirect("/");
        
    }

}
