import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB{
    private static final String URL_JDBC_SQLITE = "jdbc:sqlite:banco_de_dados_produtos.db";

    //Método conectar sqlite
    public static Connection conectar(){
        try{
            return DriverManager.getConnection(URL_JDBC_SQLITE);
        } catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    //Método para conectar com url, usuário e senha (outros db)
    public static Connection conectarGenerico(String url, String usuario, String senha){
        try{
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}