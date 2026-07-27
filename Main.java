// **Esta classe esta sendo utilizada para testagem de todas as principais funções de 'ProdutoDAO'
// relativas a operações do banco de dados.**

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        try (Connection conexao = ConexaoDB.conectar()){
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                exibirMenu();
                opcao = lerOpcao(scanner);

                switch (opcao){
                    case 1 -> mostrarProdutos(produtoDAO);
                    case 2 -> testarInserir(produtoDAO);
                    case 3 -> testarConsultarPorId(produtoDAO);
                    case 4 -> testarAtualizar(produtoDAO);
                    case 5 -> testarExcluir(produtoDAO);
                    case 6 -> testarExcluirTodos(produtoDAO);
                    case 0 -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e){
            System.err.println("Erro geral: " + e.getMessage());
        }
    }

    private static void exibirMenu(){
        System.out.println("\n===== TESTE - GERENCIADOR DE ESTOQUE =====");
        System.out.println("1 - Listar produtos");
        System.out.println("2 - Testar inserir");
        System.out.println("3 - Testar consultar por ID (id=1)");
        System.out.println("4 - Testar atualizar (id=1)");
        System.out.println("5 - Testar excluir (id=1)");
        System.out.println("6 - Testar excluir todos");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner scanner){
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e){
            return -1; // força "default" do switch
        }
    }

    public static void mostrarProdutos(ProdutoDAO produtoDAO){
        List<Produto> todosProdutos = produtoDAO.listarTodos();
        if (todosProdutos.isEmpty()){
            System.out.println("Nenhum produto encontrado.\n");
        } else {
            System.out.println("\nLista de produtos:");
            for (Produto produto : todosProdutos) {
                System.out.println("ID: " + produto.getId() +
                        " | Nome: " + produto.getNome() +
                        " | Quantidade: " + produto.getQuantidade() +
                        " | Preço: R$ " + produto.getPreco() +
                        " | Status: " + produto.getStatus());
            }
        }
    }

    private static void testarInserir(ProdutoDAO produtoDAO){
        Produto novoProduto1 = new Produto("Notebook", 15, 2599.99, "Em estoque");
        Produto novoProduto2 = new Produto("Mass Effect 2", 30, 159.90, "Em estoque");
        Produto novoProduto3 = new Produto("Nvidia RTX 5090", 5, 24999.99, "Estoque baixo");

        produtoDAO.inserir(novoProduto1);
        produtoDAO.inserir(novoProduto2);
        produtoDAO.inserir(novoProduto3);

        System.out.println("Produtos inseridos!");
        mostrarProdutos(produtoDAO);
    }

    private static void testarConsultarPorId(ProdutoDAO produtoDAO){
        Produto produto = produtoDAO.consultarPorId(1);
        if (produto != null){
            System.out.println("Produto encontrado: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado na base de dados.");
        }
    }

    private static void testarAtualizar(ProdutoDAO produtoDAO){
        Produto produto = produtoDAO.consultarPorId(1);
        if (produto == null){
            System.out.println("Produto não encontrado.");
            return;
        }

        produto.setNome("Final Fantasy VII - PSA 10");
        produto.setPreco(1499.99);
        produtoDAO.atualizar(produto);

        System.out.println("Produto id=1 atualizado (nome e preço)!");
        mostrarProdutos(produtoDAO);
    }

    private static void testarExcluir(ProdutoDAO produtoDAO){
        produtoDAO.excluir(1);
        System.out.println("Produto id=1 excluído (se existia)!");
        mostrarProdutos(produtoDAO);
    }

    private static void testarExcluirTodos(ProdutoDAO produtoDAO){
        produtoDAO.excluirTodos();
        System.out.println("Todos os produtos foram excluídos!");
        mostrarProdutos(produtoDAO);
    }
}
