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
import model.Servico;

/**
 *
 * @author michel
 */
public class ServicoDAO {
    
    public ServicoDAO(){
        
    }
    
    public void insert(Servico_peca servico_peca) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO servico_peca (quantidade, servico_id, peca_id, valor) VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1, servico_peca.getQuantidade());
            preparedStatement.setInt(2, servico_peca.getServico());
            preparedStatement.setInt(3, servico_peca.getPeca().getId());
            preparedStatement.setDouble(4, servico_peca.getValor());
            
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClassificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClassificadoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Servico_peca> select() throws SQLException {
        ArrayList<Servico_peca> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM servico_peca;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Servico_peca(resultSet.getInt("id"), resultSet.getInt("quantidade"), resultSet.getInt("servico_id"), new PecaDAO().selectById(resultSet.getInt("peca_id")), resultSet.getDouble("valor")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Servico_peca selectById(int id) throws SQLException {
        Servico_peca servico_peca;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM servico_peca WHERE servico_id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            servico_peca = new Servico_peca();
            if (resultSet.next()) {
                
                servico_peca = new Servico_peca(resultSet.getInt("id"), resultSet.getInt("quantidade"), resultSet.getInt("servico_id"), new PecaDAO().selectById(resultSet.getInt("peca_id")), resultSet.getDouble("valor"));
            }else {
                
            }
        }
        return servico_peca;
    }
}
