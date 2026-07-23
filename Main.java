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

            //Teste consulta por ID
            Produto produtoConsultado = produtoDAO.consultarPorId(1);
            if (produtoConsultado != null){
                System.out.println("\nProduto encontrado: " + produtoConsultado.getNome());
            } else {
                System.out.println("\nProduto não encontrado na base de dados.");
            }


        } catch (SQLException e){
            System.err.println("Erro geral: " + e.getMessage());
        }
    }


    public static void mostrarProdutos(ProdutoDAO produtoDAO){
        List<Produto> todosProdutos = produtoDAO.listarTodos();
        if (todosProdutos.isEmpty()){
            System.out.println("Nenhum produto encontrado.\n");
        } else {
            System.out.println("Lista de produtos:");
            for (Produto produto : todosProdutos) {
                System.out.println("ID: " + produto.getId() +
                        " | Nome: " + produto.getNome() +
                        " | Quantidade: " + produto.getQuantidade() +
                        " | Preço: R$ " + produto.getPreco() +
                        " | Status: " + produto.getStatus());
            }
        }
    }
}