package sample.control;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.*;

import java.sql.SQLException;
import java.util.List;

public class CriaLista {
    @FXML
    private JFXButton btAdciona;

    @FXML
    private JFXButton btEncerrar;

    @FXML
    private JFXButton btFechar;

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
    private JFXButton btAdd;

    @FXML
    private ListView ltvListas;

    @FXML
    private Label lbLista;

    @FXML
    private ProgressBar pbCarrega;

    private ObservableList<Item> lista = FXCollections.observableArrayList();
    private ObservableList<Item> listagem = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
        tcItemNome.setCellValueFactory(new PropertyValueFactory<>("produtoNome"));
        tcItemMarca.setCellValueFactory(new PropertyValueFactory<>("produtoMarca"));
        tcItemValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcItemComercio.setCellValueFactory(new PropertyValueFactory<>("comercioNome"));
        tcItemCidade.setCellValueFactory(new PropertyValueFactory<>("comercioCidade"));
        tcItemEstado.setCellValueFactory(new PropertyValueFactory<>("comercioEstado"));
        tcItemUsuario.setCellValueFactory(new PropertyValueFactory<>("responsavel"));

        tvItem.setItems(listagem);
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        ObservableList<Comercio> comercios = FXCollections.observableArrayList();
        comercios.addAll(Controle.getInstance().listaComercios());

        pbCarrega.setVisible(true);

        Task<Void> carrega = carregaDados();
        Thread backgroundThread = new Thread(carrega);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    private Task<Void> carregaDados(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<Item> lista = Controle.getInstance().listaItems();
                Platform.runLater(()->{
                    listagem.addAll(lista);
                    pbCarrega.setVisible(false);
                });
                System.out.println(listagem);
                return null;
            }
        };
    }

    @FXML
    public void adcionarLista(){
        lbLista.setVisible(false);
        ltvListas.setVisible(false);
        btAdd.setVisible(false);
        tvItem.setVisible(true);
        btAdciona.setVisible(true);
        btEncerrar.setVisible(true);
        lista.clear();
    }

    public void adcionaItem(ActionEvent event) {
        Item item = tvItem.getSelectionModel().getSelectedItem();

        if(item != null) {
            lista.add(item);
        }
    }

    public void encerraLista(ActionEvent event) {


        lbLista.setVisible(true);
        ltvListas.setVisible(true);
        btAdd.setVisible(true);
        tvItem.setVisible(false);
        btAdciona.setVisible(false);
        btEncerrar.setVisible(false);
    }
}
