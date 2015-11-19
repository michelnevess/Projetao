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

/**
 *
 * @author michel
 */
public class PecaDAO {
    
    public PecaDAO(){
        
    }
    
    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE peca SET ativo = ? WHERE id = ?;")) {;
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }

    public void insert(Peca peca) throws SQLException {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO peca (nome, valor, fornecedor) VALUES (?, ?, ?);");
            preparedStatement.setString(1, peca.getNome());
            preparedStatement.setDouble(2, peca.getValor());
            preparedStatement.setString(3, peca.getFornecedor());
            preparedStatement.executeQuery();
        } catch (SQLException sqle) {
            
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Peca> select() throws SQLException {
        ArrayList<Peca> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM peca WHERE ativo = true;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Peca(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("fornecedor"), resultSet.getDouble("valor")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Peca selectById(int id) throws SQLException {
        Peca peca;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM peca WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            peca = new Peca();
            if (resultSet.next()) {
                
                peca = new Peca(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("fornecedor"), resultSet.getDouble("valor"));
            }else {
                
            }
        }
        return peca;
    }

    public void update(Peca peca) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE peca SET nome = ?, fornecedor = ?, valor = ? WHERE id = ?;")) {;
            preparedStatement.setString(1, peca.getNome());
            preparedStatement.setString(2, peca.getFornecedor());
            preparedStatement.setDouble(3, peca.getValor());
            preparedStatement.setInt(4, peca.getId() );
            preparedStatement.executeUpdate();
         
        }
    }
    
}
