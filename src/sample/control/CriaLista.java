package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CriaLista extends Mensagem{
    @FXML
    private JFXComboBox<Integer> cbQuantidade;

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
    private ListView<Lista> ltvListas;

    @FXML
    private Label lbLista;

    @FXML
    private ProgressBar pbCarrega;

    private Lista lista = null;
    private ObservableList<Item> listagem = FXCollections.observableArrayList();
    private ObservableList<Lista> listagem2 = FXCollections.observableArrayList();


    @FXML
    void initialize() throws SQLException {
        lbLista.setDisable(false);
        ltvListas.setDisable(false);
        btAdd.setDisable(false  );
        tvItem.setDisable(true);
        btAdciona.setDisable(true);
        btEncerrar.setDisable(true);
        ObservableList<Integer> quantidade = FXCollections.observableArrayList();
        tcItemNome.setCellValueFactory(new PropertyValueFactory<>("produtoNome"));
        tcItemMarca.setCellValueFactory(new PropertyValueFactory<>("produtoMarca"));
        tcItemValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcItemComercio.setCellValueFactory(new PropertyValueFactory<>("comercioNome"));
        tcItemCidade.setCellValueFactory(new PropertyValueFactory<>("comercioCidade"));
        tcItemEstado.setCellValueFactory(new PropertyValueFactory<>("comercioEstado"));
        tcItemUsuario.setCellValueFactory(new PropertyValueFactory<>("responsavel"));

        tvItem.setItems(listagem);
        ltvListas.setItems(listagem2);
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        ObservableList<Comercio> comercios = FXCollections.observableArrayList();
        comercios.addAll(Controle.getInstance().listaComercios());

        for(int i = 1; i < 100; i++){
            quantidade.add(i);
        }

        cbQuantidade.setItems(quantidade);
        pbCarrega.setVisible(true);
        atualizaLista();
    }

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    private void atualizaLista(){
        Task<Void> carrega = carregaDados();
        Thread backgroundThread = new Thread(carrega);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    private void atualizaListas(){
        Task<Void> carrega = carregaLista();
        Thread backgroundThread = new Thread(carrega);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    private Task<Void> carregaDados(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                listagem.clear();
                List<Item> lista = Controle.getInstance().listaItems();
                Platform.runLater(()->{
                    listagem.addAll(lista);
                    pbCarrega.setVisible(false);
                    try {
                        if(Controle.getInstance().listaItems() != null){
                            atualizaListas();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
    }

    private Task<Void> carregaLista(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                listagem2.clear();
                List<Lista> listas = Controle.getInstance().carregaListas();
                Platform.runLater(()->{
                    listagem2.addAll(listas);
                });

                return null;
            }
        };
    }

    @FXML
    public void adcionarLista(){
        lbLista.setDisable(true);
        ltvListas.setDisable(true);
        btAdd.setDisable(true);
        tvItem.setDisable(false);
        btAdciona.setDisable(true);
        btEncerrar.setDisable(true);
        lista = new Lista();
    }

    public void adcionaItem() {
        if(cbQuantidade.getSelectionModel().getSelectedItem() != null) {
            btEncerrar.setDisable(false);
            Item item = tvItem.getSelectionModel().getSelectedItem();
            int quantidade = cbQuantidade.getValue();

            ListaItem listaItem = new ListaItem(item, quantidade);
            lista.addItem(listaItem);
        }
        else{
            Aviso(Alert.AlertType.WARNING, "Selecione uma quantidade");
        }
    }

    public void encerraLista(ActionEvent event) throws SQLException {
        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Browser.NOMEARQUIVO));

        try {
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);
            ButtonType proximoButtonType = new ButtonType("Próximo", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(proximoButtonType);
            boolean flag = false;
            while (!flag) {
                Optional<ButtonType> res = dialog.showAndWait();

                if (!dialog.isShowing()) {
                    flag = true;
                }

                if (res.isPresent() && res.get() == proximoButtonType) {
                    NomeLista controle = loader.getController();

                    lista.setNome(controle.retornaNome());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        Date data =  new Date(System.currentTimeMillis());

        lista.setDataAtualizacao(data);
        lista.setUsuario(Controle.getUsuario());
        lbLista.setDisable(false);
        ltvListas.setDisable(false);
        btAdd.setDisable(false);
        tvItem.setDisable(true);
        btAdciona.setDisable(true);
        btEncerrar.setDisable(true);
        cbQuantidade.setDisable(true);
        Controle.getInstance().salvaLista(lista);
        atualizaListas();
    }

    public void abilitaAdd(){
        btAdciona.setDisable(false);
    }
}
