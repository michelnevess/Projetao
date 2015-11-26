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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peca;
import model.Servico_peca;

/**
 *
 * @author michel
 */
public class Servico_pecaDAO {
    
    public Servico_pecaDAO(){
        
    }
    public void insert(Servico_peca servico_peca) throws SQLException {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO servico_peca (quantidade, servico_id, peca_id, valor) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, servico_peca.getQuantidade());
            preparedStatement.setInt(2, servico_peca.getServico());
            preparedStatement.setInt(3, servico_peca.getPeca().getId());
            preparedStatement.setDouble(4, servico_peca.getValor());
            preparedStatement.executeQuery();
        } catch (SQLException sqle) {
            
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Servico_pecaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Servico_pecaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Servico_peca> select(int id) throws SQLException {
        ArrayList<Servico_peca> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT id, quantidade, peca_id, valor FROM servico_peca WHERE servico_id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Servico_peca(resultSet.getInt("id"), resultSet.getInt("quantidade"), id, new PecaDAO().selectById(resultSet.getInt("peca_id")), resultSet.getDouble("valor")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public void update(Servico_peca servico_peca) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE servico_peca SET quantidade = ?, servico_id = ?, peca_id = ?, valor = ? WHERE id = ?;")) {;
            preparedStatement.setInt(1, servico_peca.getQuantidade());
            preparedStatement.setInt(2, servico_peca.getServico());
            preparedStatement.setInt(3, servico_peca.getPeca().getId());
            preparedStatement.setDouble(4, servico_peca.getValor());
            preparedStatement.setInt(5, servico_peca.getId() );
            preparedStatement.executeUpdate();
         
        }
    }
    
    public double total(int id) throws SQLException{
        double total;
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT servico.valor, servico.descricao, SUM(servico_peca.quantidade*servico_peca.valor) AS total FROM servico_peca INNER JOIN servico ON(servico.id = servico_peca.servico_id) WHERE servico_peca.servico_id = ? GROUP BY servico.descricao, servico.valor, total ");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getDouble("total") + resultSet.getDouble("valor");
    }
}
