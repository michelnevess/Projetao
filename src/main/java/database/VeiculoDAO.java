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
import model.Veiculo;

/**
 *
 * @author michel
 */
public class VeiculoDAO {
 
    public VeiculoDAO(){
        
    }
    
    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE veiculo SET ativo = ? WHERE id = ?;")) {;
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }

    public void insert(Veiculo veiculo) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO veiculo (modelo, marca, ano, placa, chassi, cliente_id, ativo) VALUES (?, ?, ?, ?, ?, ?, true);");
            preparedStatement.setString(1, veiculo.getModelo());
            preparedStatement.setString(2, veiculo.getMarca());
            preparedStatement.setString(3, veiculo.getAno());
            preparedStatement.setString(4, veiculo.getPlaca());
            preparedStatement.setString(5, veiculo.getChassi());
            preparedStatement.setInt(6, veiculo.getCliente().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Veiculo> select() throws SQLException {
        ArrayList<Veiculo> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM veiculo WHERE ativo = true;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Veiculo(resultSet.getInt("id"), resultSet.getString("modelo"), resultSet.getString("marca"), resultSet.getString("ano"), resultSet.getString("placa"), resultSet.getString("chassi"), new ClienteDAO().selectById(resultSet.getInt("cliente_id"))));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Veiculo selectById(int id) throws SQLException {
        Veiculo veiculo;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM veiculo WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            veiculo = new Veiculo();
            if (resultSet.next()) {
                
                veiculo = new Veiculo(resultSet.getInt("id"), resultSet.getString("modelo"), resultSet.getString("marca"), resultSet.getString("ano"), resultSet.getString("placa"), resultSet.getString("chassi"), new ClienteDAO().selectById(resultSet.getInt("cliente_id")));
            }else {
                
            }
        }
        return veiculo;
    }

    public void update(Veiculo veiculo) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE veiculo SET modelo = ?, marca = ?, ano = ?, placa = ?, chassi = ?, cliente_id = ? WHERE id = ?;")) {;
            preparedStatement.setString(1, veiculo.getModelo());
            preparedStatement.setString(2, veiculo.getMarca());
            preparedStatement.setString(3, veiculo.getAno());
            preparedStatement.setString(4, veiculo.getPlaca());
            preparedStatement.setString(5, veiculo.getChassi());
            preparedStatement.setInt(6, veiculo.getCliente().getId());
            preparedStatement.setInt(7, veiculo.getId() );
            preparedStatement.executeUpdate();
         
        }
    }
    
}
