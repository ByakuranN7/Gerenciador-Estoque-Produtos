import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main{
    public static void main(String[] args){
        try (Connection conexao = ConexaoDB.conectar()){
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            mostrarProdutos(produtoDAO);

            //Teste inserção
            Produto novoProduto1 = new Produto("Notebook", 15, 2599.99, "Em estoque");
            Produto novoProduto2 = new Produto("Mass Effect 2", 30, 159.90, "Em estoque");
            Produto novoProduto3 = new Produto("Nvidia RTX 5090", 5, 24999.99, "Estoque baixo");
            produtoDAO.inserir(novoProduto1);
            produtoDAO.inserir(novoProduto2);
            produtoDAO.inserir(novoProduto3);

            mostrarProdutos(produtoDAO);
        } catch (SQLException e){
            System.err.println("Erro ao realizar inserção: " + e.getMessage());
        }
    }


    public static void mostrarProdutos(ProdutoDAO produtoDAO){
        List<Produto> produtos = produtoDAO.listarTodos();

        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Status: " + produto.getStatus());
            System.out.println("----------------------------");
        }
    }
}