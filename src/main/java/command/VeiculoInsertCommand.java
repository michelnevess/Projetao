/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;


import database.VeiculoDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.Veiculo;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import database.ClienteDAO;
import java.sql.SQLException;

/**
 *
 * @author michel
 */
public class VeiculoInsertCommand extends Command {
    
    

    public VeiculoInsertCommand(Request request, Response response) throws SQLException {
        super(request, response);     
        
        
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(request.queryParams("modelo"));
        veiculo.setMarca(request.queryParams("marca"));
        veiculo.setAno(request.queryParams("ano"));
        veiculo.setPlaca(request.queryParams("placa"));
        veiculo.setChassi(request.queryParams("chassi"));
        veiculo.setCliente(new ClienteDAO().selectById(Integer.parseInt(request.params("cliente"))));
        
        new VeiculoDAO().insert(veiculo);
        
        response.redirect("/");
    }    
    

}
