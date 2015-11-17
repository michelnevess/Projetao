/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Historico;

/**
 *
 * @author michel
 */
public class HistoricoDAO {
    
    public HistoricoDAO(){
    
}
    public ArrayList<Historico> select() throws SQLException {
        ArrayList<Historico> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM log;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Historico(resultSet.getInt("id"), resultSet.getInt("pk"), resultSet.getString("nome_campo"), resultSet.getString("nome_tabela"), resultSet.getString("valor_antigo"), resultSet.getString("valor_novo"), resultSet.getDate("data_modificacao"), resultSet.getString("operacao")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

}
