package command;

import database.FuncionarioDAO;
import database.PecaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;
import org.json.JSONArray;
import spark.Request;
import spark.Response;

public class Peca extends Command {

    private String resposta;

    public Peca(Request request, Response response) throws SQLException {
        super(request, response);
        JSONArray jsonArray = new JSONArray(new PecaDAO().select());
        this.resposta = jsonArray.toString();
    }
    public String getResposta() {
        return this.resposta;

    }
}
