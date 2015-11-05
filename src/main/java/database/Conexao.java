package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author iapereira
 */
public class Conexao {
    private String database;
    private String user;
    private String password;
    private String host;
    private String port;

    public Conexao() {
        this.database = "projetao";
        this.user = "postgres";
        this.password = "postgres";
        this.host = "localhost";
        this.port = "5432";
    }

    public Conexao(String database, String user, String password, String host, String port) {
        this.database = database;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public Connection getConexao() {
        try {
            String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.database;
            return DriverManager.getConnection(url, this.user, this.password);
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel realizar a conexao");
        }
    }
}
