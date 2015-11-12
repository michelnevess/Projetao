package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iapereira
 */
public class ClassificadoDAO {

    public ClassificadoDAO() {
    }


    public void delete(int id) throws SQLException {
        Connection conexao = new Conexao().getConexao();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM classificado WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        conexao.close();
    }

    public void insert(Classificado classificado) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;

        try {
            conexao = new Conexao().getConexao();
            preparedStatement = conexao.prepareStatement("INSERT INTO classificado (descricao, contato) VALUES (?, ?);");
            preparedStatement.setString(1, classificado.getDescricao());
            preparedStatement.setString(2, classificado.getContato());
            System.out.println("LinhasAfetadas:" + preparedStatement.executeUpdate()); // LINHAS AFETADAS
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
   
    public ArrayList<Classificado> select() throws SQLException {
        ArrayList<Classificado> vet = new ArrayList();
        Connection conexao = new Conexao().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM classificado;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vet.add(new Classificado(resultSet.getInt("id"), resultSet.getString("descricao"), resultSet.getString("contato")));
        }
        preparedStatement.close();
        
        conexao.close();
        return vet;
    }

    public Classificado selectById(int id) throws SQLException {
        Classificado classificado;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM classificado WHERE id = ?;")) {
            System.out.println("id:"+id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            classificado = new Classificado();
            if (resultSet.next()) {
                System.out.println("Sim");
                classificado = new Classificado(resultSet.getInt("id"), resultSet.getString("descricao"), resultSet.getString("contato"));
            }else {
                System.out.println("nao");
            }
        }
        return classificado;
    }

    public void update(Classificado classificado) throws SQLException {
         
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement preparedStatement = conexao.prepareStatement("UPDATE classificado SET descricao = ?, contato = ? WHERE id = ?;")) {;
            preparedStatement.setString(1, classificado.getDescricao() );
            preparedStatement.setString(2, classificado.getContato() );
            preparedStatement.setInt(3, classificado.getId() );
            preparedStatement.executeUpdate();
         
        }
        
        
    }
}
