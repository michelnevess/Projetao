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
    
    public void insert(Servico servico) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO servico (descricao, valor, d_inicio, d_fim, pago, funcionario_id, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, servico.getDescricao());
            preparedStatement.setDouble(2, servico.getValor());
            preparedStatement.setDate(3, servico.getD_inicio());
            preparedStatement.setDate(4, servico.getD_fim());
            preparedStatement.setBoolean(4, servico.isPago());
            preparedStatement.setInt(4, servico.getFuncionario().getId());
            preparedStatement.setInt(4, servico.getCliente().getId());
            
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (preparedStatement != null) {
                try {
                    if (!preparedStatement.isClosed()) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conexao != null) {
                try {
                    if (!conexao.isClosed()) {
                        conexao.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   
    public ArrayList<Servico> select() throws SQLException {
        ArrayList<Servico> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM servico;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Servico(resultSet.getInt("id"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getDate("d_inicio"), resultSet.getDate("d_fim"), resultSet.getBoolean("pago"), new FuncionarioDAO().selectById(resultSet.getInt("funcionario_id")), new ClienteDAO().selectById(resultSet.getInt("cliente_id"))));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Servico selectById(int id) throws SQLException {
        Servico servico;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM servico WHERE servico_id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            servico = new Servico();
            if (resultSet.next()) {
                
                servico = new Servico(resultSet.getInt("id"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getDate("d_inicio"), resultSet.getDate("d_fim"), resultSet.getBoolean("pago"), new FuncionarioDAO().selectById(resultSet.getInt("funcionario_id")), new ClienteDAO().selectById(resultSet.getInt("cliente_id")));
            }else {
                
            }
        }
        return servico;
    }
    
    public Servico ultimo() throws SQLException {
        Servico ultimo = new Servico();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM servico ORDER BY id DESC LIMIT 1;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ultimo = new Servico(resultSet.getInt("id"), resultSet.getString("descricao"), resultSet.getDouble("valor"), resultSet.getDate("d_inicio"), resultSet.getDate("d_fim"), resultSet.getBoolean("pago"), new FuncionarioDAO().selectById(resultSet.getInt("funcionario_id")), new ClienteDAO().selectById(resultSet.getInt("cliente_id")));
        }
        preparedStatement.close();
        
        conexao.close();
        return ultimo;
    }
    
    public void update(Servico servico) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE servico SET descricao = ?, valor = ?, d_inicio = ?, d_fim = ?, funcionario_id = ?, cliente_id = ? WHERE id = ?;")) {;
            preparedStatement.setString(1, servico.getDescricao());
            preparedStatement.setDouble(2, servico.getValor());
            preparedStatement.setDate(3, servico.getD_inicio());
            preparedStatement.setDate(4, servico.getD_fim());
            preparedStatement.setInt(6, servico.getFuncionario().getId());
            preparedStatement.setInt(6, servico.getCliente().getId());
            preparedStatement.setInt(7, servico.getId() );
            preparedStatement.executeUpdate();
         
        }
    }
    
    public void pago(int id) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE servico SET pago = ? WHERE id = ?;")) {;
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
         
        }
    }
    
}
