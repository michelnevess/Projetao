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
import model.Cliente;
import model.Endereco;

/**
 *
 * @author michel
 */
public class ClienteDAO {

    public ClienteDAO(){
        
    }
    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM cliente WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }
 
    public void insert(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO cliente (nome, telefone, email, endereco_id, cpf, cnpj, fisico, ativo) VALUES (?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setInt(4, cliente.getEndereco().getId());
            preparedStatement.setString(5, cliente.getCpf());
            preparedStatement.setString(6, cliente.getCnpj());
            preparedStatement.setBoolean(7, cliente.isFisico());
            
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
   
    public ArrayList<Cliente> select() throws SQLException {
        ArrayList<Cliente> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cliente;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("cpf"), resultSet.getString("cnpj"), resultSet.getString("telefone"), resultSet.getBoolean("ativo"), resultSet.getBoolean("fisico"), new EnderecoDAO().selectById(resultSet.getInt("endereco_id"))));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Cliente selectById(int id) throws SQLException {
        Cliente cliente;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cliente WHERE id = ?;")) {
            System.out.println("id:"+id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            cliente = new Cliente();
            if (resultSet.next()) {
                
                cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("cpf"), resultSet.getString("cnpj"), resultSet.getString("telefone"), resultSet.getBoolean("ativo"), resultSet.getBoolean("fisico"), new EnderecoDAO().selectById(resultSet.getInt("endereco_id")));
            }else {
                
            }
        }
        return cliente;
    }
}
