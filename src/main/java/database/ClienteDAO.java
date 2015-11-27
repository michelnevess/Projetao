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
        try (PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE cliente SET ativo = ? WHERE id = ?;")) {;
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }
 
    public void insert(Cliente cliente) throws SQLException {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO cliente (nome, telefone, email, endereco_id, cpf, cnpj, fisico, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, true);");
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setInt(4, cliente.getEndereco().getId());
            preparedStatement.setString(5, cliente.getCpf());
            preparedStatement.setString(6, cliente.getCnpj());
            preparedStatement.setBoolean(7, cliente.isFisico());
            preparedStatement.executeQuery();
        } catch (SQLException sqle) {
            
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Cliente> select() throws SQLException {
        ArrayList<Cliente> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cliente WHERE ativo = true;");
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
    
    public void update(Cliente cliente) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE cliente SET nome = ?, telefone = ?, email = ?, endereco_id = ?, cpf = ?, cnpj = ?, fisico = ?, ativo = true WHERE id = ?;")) {;
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setInt(4, cliente.getEndereco().getId());
            preparedStatement.setString(5, cliente.getCpf());
            preparedStatement.setString(6, cliente.getCnpj());
            preparedStatement.setBoolean(7, cliente.isFisico());
            preparedStatement.setInt(8, cliente.getId() );
            preparedStatement.executeUpdate();
         
        }

    }
    
    public boolean cnpj(String cnpj) throws SQLException {
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM cliente WHERE cnpj = ?;")) {
            preparedStatement.setString(1, cnpj);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        }
    }
    
    

}