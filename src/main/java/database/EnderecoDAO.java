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
import model.Endereco;

/**
 *
 * @author michel
 */
public class EnderecoDAO {

    public EnderecoDAO(){
        
    }
    

    public void insert(Endereco endereco) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO endereco (estado, cidade, bairro, rua, numero, complemento) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, endereco.getEstado());
            preparedStatement.setString(2, endereco.getCidade());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setString(4, endereco.getRua());
            preparedStatement.setString(5, endereco.getNumero());
            preparedStatement.setString(6, endereco.getComplemento());
            
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Endereco> select() throws SQLException {
        ArrayList<Endereco> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM endereco");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Endereco(resultSet.getInt("id"), resultSet.getString("estado"), resultSet.getString("cidade"), resultSet.getString("bairro"), resultSet.getString("rua"), resultSet.getString("numero"), resultSet.getString("complemento")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Endereco selectById(int id) throws SQLException {
        Endereco endereco;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM endereco WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            endereco = new Endereco();
            if (resultSet.next()) {
                
                endereco = new Endereco(resultSet.getInt("id"), resultSet.getString("estado"), resultSet.getString("cidade"), resultSet.getString("bairro"), resultSet.getString("rua"), resultSet.getString("numero"), resultSet.getString("complemento"));
            }else {
                
            }
        }
        return endereco;
    }

    public void update(Endereco endereco) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE endereco SET estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ? WHERE id = ?;")) {;
            preparedStatement.setString(1, endereco.getEstado());
            preparedStatement.setString(2, endereco.getCidade());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setString(4, endereco.getRua());
            preparedStatement.setString(5, endereco.getNumero());
            preparedStatement.setString(6, endereco.getComplemento());
            preparedStatement.setInt(7, endereco.getId() );
            preparedStatement.executeUpdate();
         
        }
    }

    private Object Endereco() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Endereco ultimo() throws SQLException {
        Endereco ultimo = new Endereco();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM endereco ORDER BY id DESC LIMIT 1;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ultimo = new Endereco(resultSet.getInt("id"), resultSet.getString("estado"), resultSet.getString("cidade"), resultSet.getString("bairro"), resultSet.getString("rua"), resultSet.getString("numero"), resultSet.getString("complemento"));
        }
        preparedStatement.close();
        
        conexao.close();
        return ultimo;
    }

    
}


