package sample.control;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Browser;
import sample.model.Item;

import java.sql.SQLException;

public class PesquisaItens {

    @FXML
    private JFXButton btFechar;

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TableView<Item> tvItem;

    @FXML
    private TableColumn<Item, String> tcItemNome;

    @FXML
    private TableColumn<Item, String> tcItemMarca;

    @FXML
    private TableColumn<Item, Float> tcItemValor;

    @FXML
    private TableColumn<Item, String> tcItemComercio;

    @FXML
    private TableColumn<Item, String> tcItemCidade;

    @FXML
    private TableColumn<Item, String> tcItemEstado;

    @FXML
    private TableColumn<Item, String> tcItemUsuario;

    @FXML
    void initialize() throws SQLException {
        tcItemNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcItemMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tcItemValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcItemComercio.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcItemCidade.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcItemEstado.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcItemUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ObservableList<Item> listagem = FXCollections.observableArrayList();

        //listagem = atualizaTabela();

        tvItem.setItems(listagem);
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    public void mostraDescricao(SortEvent<TableView> tableViewSortEven) {

    }


}
