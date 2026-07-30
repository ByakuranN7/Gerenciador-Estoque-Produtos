import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;

import java.util.List;

public class ProdutoGUI extends Application{
    private ProdutoDAO produtoDAO;
    private ObservableList<Produto> todosProdutos;
    private TableView<Produto> tableView;
    private TextField nomeInput, quantidadeInput, precoInput;
    private ComboBox<String> statusComboBox;
    private Connection conexaoDB;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage palco){
        conexaoDB = ConexaoDB.conectar();
        produtoDAO = new ProdutoDAO(conexaoDB); //inicializa o objeto
        todosProdutos = FXCollections.observableArrayList(produtoDAO.listarTodos());

        palco.setTitle("Gerenciamento de Estoque de Produtos");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        HBox nomeProdutoBox = new HBox();
        nomeProdutoBox.setSpacing(10);
        Label nomeLabel = new Label("Produto:");
        nomeInput = new TextField();
        nomeProdutoBox.getChildren().addAll(nomeLabel, nomeInput);

        HBox quantidadeBox = new HBox();
        quantidadeBox.setSpacing(10);
        Label quantidadeLabel = new Label("Quantidade:");
        quantidadeInput = new TextField();
        quantidadeBox.getChildren().addAll(quantidadeLabel, quantidadeInput);

        HBox precoBox = new HBox();
        precoBox.setSpacing(10);
        Label precoLabel = new Label("Preço:");
        precoInput = new TextField();
        precoBox.getChildren().addAll(precoLabel, precoInput);

        HBox statusBox = new HBox();
        statusBox.setSpacing(10);
        Label statusLabel = new Label("Status:");
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Estoque Normal", "Estoque Baixo");
        statusBox.getChildren().addAll(statusLabel, statusComboBox);

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> {
            String preco = precoInput.getText().replace(',', '.'); //Substitui virgula por ponto no preço
            Produto produto = new Produto(nomeInput.getText(),
                    Integer.parseInt(quantidadeInput.getText()),
                    Double.parseDouble(preco),
                    statusComboBox.getValue());
            produtoDAO.inserir(produto);
            todosProdutos.setAll(produtoDAO.listarTodos());
            //limparCampos(); //******NÃO FIZ ESSE METODO AINDA**********
        });
    }
}
