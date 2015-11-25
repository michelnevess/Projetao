package command;

import database.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;
import org.json.JSONArray;
import spark.Request;
import spark.Response;

public class Filtro2 extends Command {

    private String resposta;

    public Filtro2(Request request, Response response) throws SQLException {
        super(request, response);
        JSONArray jsonArray = new JSONArray(new FuncionarioDAO().filtro(request.params(":valor")));
        this.resposta = jsonArray.toString();
/*
        String outp = "[";
        ArrayList<Funcionario> funcionario = new FuncionarioDAO().filtro(request.params(":valor"));
        
        for (int i = 0; i < funcionario.size(); i++) {
            outp += "{id:" + funcionario.get(i).getId() + ",";
            outp += "nome:" + funcionario.get(i).getNome() + ",";
            outp += "cpf:" + funcionario.get(i).getCpf() + ",";
            outp += "endereco:" + funcionario.get(i).getEndereco().toString() + ",";
            outp += "telefone:" + funcionario.get(i).getTelefone() + ",";
            outp += "email:" + funcionario.get(i).getEmail() + "}";

            

        }
        outp += "]";
        this.resposta = outp;
    }
*/
    }
    public String getResposta() {
        return this.resposta;

    }
}
