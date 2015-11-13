
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
import model.Funcionario;

/**
 *
 * @author michel
 */
public class FuncionarioDAO {
 
    public FuncionarioDAO(){
        
    }
    
    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE funcionario SET ativo = ? WHERE id = ?;")) {;
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }
 
    public void insert(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO funcionario (nome, telefone, email, endereco_id, cpf, ativo) VALUES (?, ?, ?, ?, ?, true);");
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getTelefone());
            preparedStatement.setString(3, funcionario.getEmail());
            preparedStatement.setInt(4, funcionario.getEndereco().getId());
            preparedStatement.setString(5, funcionario.getCpf());
            
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
   
    public ArrayList<Funcionario> select() throws SQLException {
        ArrayList<Funcionario> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM funcionario;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("cpf"), resultSet.getString("telefone"), resultSet.getBoolean("ativo"), new EnderecoDAO().selectById(resultSet.getInt("endereco_id"))));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Funcionario selectById(int id) throws SQLException {
        Funcionario funcionario;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM funcionario WHERE id = ?;")) {
            System.out.println("id:"+id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            funcionario = new Funcionario();
            if (resultSet.next()) {
                
                funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("cpf"), resultSet.getString("telefone"), resultSet.getBoolean("ativo"), new EnderecoDAO().selectById(resultSet.getInt("endereco_id")));
            }else {
                
            }
        }
        return funcionario;
    }
}
