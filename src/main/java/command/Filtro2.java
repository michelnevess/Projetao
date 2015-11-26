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
    }
    public String getResposta() {
        return this.resposta;

    }
}
