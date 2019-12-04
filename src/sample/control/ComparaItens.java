package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.Controle;
import sample.model.Produto;

import java.sql.SQLException;

public class ComparaItens {
    @FXML
    private JFXComboBox<Produto> cbProdutoUm;

    @FXML
    private JFXComboBox<Produto> cbProdutoDois;

    @FXML
    private ListView ltvComparacao;

    @FXML
    private JFXButton btFechar;

    private ObservableList<Produto> produtos = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        produtos.addAll(Controle.getInstance().listaProdutos());
        cbProdutoUm.setItems(produtos);
        cbProdutoDois.setItems(produtos);
    }

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

}
