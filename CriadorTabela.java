import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class CriadorTabela{
    public static void main(String[] args){
        try (Connection conexao = ConexaoDB.conectar();
            Statement stmt = conexao.createStatement()){

            //Definindo o comando SQL para criar a tabela
            String comandoSQL = "CREATE TABLE produtos (" +
                    "id_produto INTEGER PRIMARY KEY," +
                    "nome_produto TEXT NOT NULL," +
                    "quantidade INTEGER," +
                    "preco REAL," +
                    "status TEXT" +
                    ");";

            System.out.println(comandoSQL);

            //Executando o comando SQL
            stmt.execute(comandoSQL);

            System.out.println("Tabela 'Produtos' criada com sucesso!");
        } catch (SQLException e){
            System.err.println("Erro ao criar a tabela 'Produtos': " + e.getMessage());
            e.printStackTrace();
        }
    }
}