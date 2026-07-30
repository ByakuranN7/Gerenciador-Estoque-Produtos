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
    }
}
