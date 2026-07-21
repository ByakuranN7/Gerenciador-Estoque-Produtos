import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO{
    private final Connection CONEXAO_DB;

    //Construtor que inicializa a conexão com o banco de dados
    public ProdutoDAO(Connection conexao){
        this.CONEXAO_DB = conexao;
    }

    //inserir no banco
    public void inserir(Produto produto){
        String sql = "INSERT INTO produtos (nome_produto, quantidade, preco, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setString(4, produto.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public void atualizar(Produto produto){

    }

    public void excluir(int id){

    }

    //excluir todos os produtos do banco
    public void excluirTodos(){
        String sql = "DELETE FROM produtos";
        try (PreparedStatement stmt = CONEXAO_DB.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir todos os produtos: " + e.getMessage());
        }
    }

    public void consultarPorId(int id){

    }

    public void listarTodos(){
        
    }


}