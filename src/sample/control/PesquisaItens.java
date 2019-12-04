package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.*;
import sample.modelDAO.ItemDAO;
import sample.modelDAO.ItemDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class PesquisaItens extends Mensagem{

    @FXML
    private JFXButton btFechar;
    @FXML
    private JFXTextField tfPesquisa;

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
    private TextArea taDescricao;

    @FXML
    private JFXComboBox<Cidade> cbCidade;

    @FXML
    private JFXComboBox<Comercio> cbComercio;

    @FXML
    private ProgressBar pbCarrega;

    private ObservableList<Item> listagem = FXCollections.observableArrayList();






//    public Task<Void> atualizaProgresso(){
//        return new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                while(true){
//                    int contProgresso=0;
//                    while(true){
//                        if(){
//                            contProgresso+=10;
//                            if(contProgresso>100){
//                                contProgresso=0;
//                            }
//                            this.updateProgress(contProgresso,100);
//                            Thread.sleep(1000);
//                        }else{
//                            Thread.sleep(100);
//                        }
//                    }
//                }
//            }
//        };
//    }

    @FXML
    void initialize() throws SQLException {
        tcItemNome.setCellValueFactory(new PropertyValueFactory<>("produtoNome"));
        tcItemMarca.setCellValueFactory(new PropertyValueFactory<>("produtoMarca"));
        tcItemValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcItemComercio.setCellValueFactory(new PropertyValueFactory<>("comercioNome"));
        tcItemCidade.setCellValueFactory(new PropertyValueFactory<>("comercioCidade"));
        tcItemEstado.setCellValueFactory(new PropertyValueFactory<>("comercioEstado"));
        tcItemUsuario.setCellValueFactory(new PropertyValueFactory<>("responsavel"));

        listagem.addAll(Controle.getInstance().listaItems());

        taDescricao.setDisable(true);
        Font font = Font.font(36);
        taDescricao.fontProperty().set(font);
//        System.out.println("listagem="+listagem);
        tvItem.setItems(listagem);
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        ObservableList<Comercio> comercios = FXCollections.observableArrayList();
        comercios.addAll(Controle.getInstance().listaComercios());

        cbCidade.setItems(cidades);
        cbComercio.setItems(comercios);


        pbCarrega.setVisible(true);

        Task<Void> carrega = carregaDados();
        Thread backgroundThread = new Thread(carrega);
        backgroundThread.setDaemon(true);
        backgroundThread.start();



    }


    public Task<Void> carregaDados(){
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
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }


    public void voltar(ActionEvent event) {
        Browser.loadWindows(Browser.MENU);
    }

    public void mostraDescricao(MouseEvent mouseEvent) {
        taDescricao.clear();
        taDescricao.setDisable(false);
        Font font = Font.font(14);
        taDescricao.fontProperty().set(font);
        Item item = tvItem.getSelectionModel().getSelectedItem();
        System.out.println(item.mostra());
        taDescricao.setText(item.mostra());
        System.out.println(item);
    }

    public void verificaTecla(KeyEvent keyEvent) throws SQLException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            pesquisarItens();
        }
    }

    public void pesquisarItens() throws SQLException{
        String nome = tfPesquisa.getText();
        ObservableList<Item> items = FXCollections.observableArrayList();

        items = Controle.getInstance().pesquisaItem(nome);

        if(nome.length() == 0){
            items.addAll(listagem);
        }

        tvItem.setItems(items);
    }

    public void procuroComercio() throws SQLException {
        Comercio comercio = cbComercio.getSelectionModel().getSelectedItem();

        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(Controle.getInstance().pesquisaComercio(comercio));
        tvItem.setItems(items);
    }

    public void procuraCidade() throws SQLException{
        Cidade cidade = cbCidade.getSelectionModel().getSelectedItem();

        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(Controle.getInstance().pesquisaCidade(cidade));
        tvItem.setItems(items);
    }
}
