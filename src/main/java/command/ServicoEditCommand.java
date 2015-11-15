package command;

import database.ClienteDAO;
import database.FuncionarioDAO;
import database.PecaDAO;
import database.ServicoDAO;
import database.Servico_pecaDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        servico.setDescricao(request.queryParams("descricao"));
        servico.setD_inicio(Date.valueOf(request.queryParams("ano1")+":"+request.queryParams("mes1")+":"+request.queryParams("dia1")));
        servico.setD_fim(Date.valueOf(request.queryParams("ano2")+":"+request.queryParams("mes2")+":"+request.queryParams("dia2")));
        servico.setValor(Double.parseDouble(request.queryParams("valor")));
        servico.setCliente(new ClienteDAO().selectById(Integer.parseInt(request.queryParams("cliente"))));
        servico.setFuncionario(new FuncionarioDAO().selectById(Integer.parseInt(request.queryParams("funcionario"))));
        
        servico.setId(Integer.parseInt(request.queryParams("id")));
        
        new ServicoDAO().update(servico);
        
        for (int i = 0; i < Integer.parseInt(request.queryParams("quantidade")); i++){
            Servico_peca sp = new Servico_peca();
            Peca peca = new PecaDAO().selectById(Integer.parseInt(request.queryParams("peca")));
        
            sp.setServico(new ServicoDAO().ultimo().getId());
            sp.setQuantidade(Integer.parseInt(request.queryParams("unidade")));
            sp.setPeca(peca);
            sp.setValor(peca.getValor());
            sp.setId(Integer.parseInt(request.queryParams("id2")));
            new Servico_pecaDAO().update(sp);
        }
        
        
            map.put("message", "Voce acaba de editar o Servico");
        
    }

}
