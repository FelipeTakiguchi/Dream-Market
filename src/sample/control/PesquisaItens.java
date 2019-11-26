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
import sample.model.*;
import sample.modelDAO.ItemDAO;
import sample.modelDAO.ItemDAOImpl;

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
    private TableColumn<Item, Produto> tcItemNome;

    @FXML
    private TableColumn<Item, Produto> tcItemMarca;

    @FXML
    private TableColumn<Item, Float> tcItemValor;

    @FXML
    private TableColumn<Item, Comercio> tcItemComercio;

    @FXML
    private TableColumn<Item, String> tcItemCidade;

    @FXML
    private TableColumn<Item, String> tcItemEstado;

    @FXML
    private TableColumn<Item, UsuarioAdm> tcItemUsuario;

    @FXML
    void initialize() throws SQLException {
        tcItemNome.setCellValueFactory(new PropertyValueFactory<>("produtoNome"));
        tcItemMarca.setCellValueFactory(new PropertyValueFactory<>("produtoMarca"));
        tcItemValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcItemComercio.setCellValueFactory(new PropertyValueFactory<>("comercioNome"));
        tcItemCidade.setCellValueFactory(new PropertyValueFactory<>("comercioCidade"));
        tcItemEstado.setCellValueFactory(new PropertyValueFactory<>("comercioEstado"));
        tcItemUsuario.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        ObservableList<Item> listagem = FXCollections.observableArrayList();

        ItemDAO itemDao = new ItemDAOImpl();

        listagem.addAll(itemDao.lista());

        System.out.println("listagem="+listagem);

        //listagem = atualizaTabela();

        tvItem.setItems(listagem);
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    public void mostraDescricao(SortEvent<TableView> tableViewSortEven) {

    }


}
