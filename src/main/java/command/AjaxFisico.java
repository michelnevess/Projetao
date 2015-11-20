/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import database.ClienteDAO;
import database.ServicoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Servico;
import spark.Request;
import spark.Response;

public class AjaxFisico extends Command {

    private String resposta;
    

    public AjaxFisico(Request request, Response response) throws SQLException {
        super(request, response);
        
        
        String descricao = "";
        String valor = "";
        String d_inicio = "";
        String d_fim = "";
        String cliente_nome = "";
        String funcionario = "";
       
               ArrayList<Servico> servico = new ServicoDAO().filtro(request.queryParams(":valor"));
               for(int i = 0; i<servico.size(); i++){
                   descricao+= "\"" + servico.get(i).getDescricao() + "\" ";
                   valor+= "\"" + servico.get(i).getValor() + "\" ";
                   d_inicio+= "\"" + servico.get(i).getD_inicio() + "\" ";
                   d_fim+= "\"" + servico.get(i).getD_fim() + "\" ";
                   cliente_nome+= "\"" + servico.get(i).getCliente().getNome() + "\" ";
                   funcionario+= "\"" + servico.get(i).getFuncionario().getNome() + "\" ";
               }
               this.resposta = "[" + descricao.trim().replace(' ', ',') + "]>"+"[" + valor.trim().replace(' ', ',') + "]>"+
                       "[" + d_inicio.trim().replace(' ', ',') + "]>"+"[" + d_fim.trim().replace(' ', ',') + "]>"+
                       "[" + cliente_nome.trim().replace(' ', ',') + "]>"+"[" + funcionario.trim().replace(' ', ',') + "]>";

    }

    public String getResposta() {
        return this.resposta;
    }

}
