package sample.control;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import sample.Browser;
import sample.model.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

public class MenuAdm {
    @FXML
    private JFXToggleButton tgModo;

    @FXML
    private JFXButton btComercio;
    @FXML
    private JFXButton btProduto;
    @FXML
    private JFXButton btItem;
    @FXML
    private JFXButton btGerenciaItem;
    @FXML
    private JFXButton btCadastra;
    @FXML
    private JFXTextField tfUm;
    @FXML
    private JFXTextField tfDois;
    @FXML
    private JFXTimePicker tpInicio;
    @FXML
    private JFXTimePicker tpFim;
    @FXML
    private JFXComboBox<Cidade> cbCidade;
    @FXML
    private JFXComboBox<Produto> cbProduto;
    @FXML
    private JFXComboBox<Comercio> cbComercio;
    @FXML
    private JFXComboBox<Item> cbItem;
    @FXML
    private TextArea taDescricao;
    @FXML
    private JFXCheckBox cbDisponivel;

    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void initialize() throws SQLException {
        limpaTela();
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        produtos.addAll(Controle.getInstance().listaProdutosAdm());
        ObservableList<Comercio> comercios = FXCollections.observableArrayList();
        comercios.addAll(Controle.getInstance().listaComercios());
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(Controle.getInstance().listaItemsAdm());

        tfUm.setVisible(false);
        tfDois.setVisible(false);
        tpInicio.setVisible(false);
        tpFim.setVisible(false);
        cbCidade.setVisible(false);
        cbProduto.setVisible(false);
        cbComercio.setVisible(false);
        cbCidade.setItems(cidades);
        cbProduto.setItems(produtos);
        cbComercio.setItems(comercios);
        cbItem.setItems(items);
    }

    public void alteraModo() {
        Browser.loadWindows(Browser.MENU);
    }

    public void cadastraComercio() {
        limpaTela();
        btComercio.setDisable(true);
        btProduto.setDisable(false);
        btItem.setDisable(false);
        btGerenciaItem.setDisable(false);
        tfUm.setVisible(true);
        tfDois.setVisible(true);
        tpInicio.setVisible(true);
        tpFim.setVisible(true);
        cbCidade.setVisible(true);
        btCadastra.setVisible(true);
        tfUm.setPromptText("Nome");
        tfDois.setPromptText("Endereço");
    }

    public void cadastraProduto() {
        limpaTela();
        btProduto.setDisable(true);
        btComercio.setDisable(false);
        btItem.setDisable(false);
        btGerenciaItem.setDisable(false);
        tfUm.setVisible(true);
        tfDois.setVisible(true);
        tfUm.setPromptText("Nome");
        tfDois.setPromptText("Marca");
        taDescricao.setVisible(true);
        btCadastra.setVisible(true);
    }

    public void cadastraItem() {
        limpaTela();
        btItem.setDisable(true);
        btGerenciaItem.setDisable(false);
        btComercio.setDisable(false);
        btProduto.setDisable(false);
        cbDisponivel.setVisible(true);
        tfUm.setVisible(true);
        tfUm.setPromptText("Preço");
        cbProduto.setVisible(true);
        cbComercio.setVisible(true);
        btCadastra.setVisible(true);
    }

    public void gerenciaItem() {
        limpaTela();
        btGerenciaItem.setDisable(true);
        btComercio.setDisable(false);
        btProduto.setDisable(false);
        btItem.setDisable(false);
        cbItem.setVisible(true);
        tfUm.setVisible(true);
        cbItem.setVisible(true);
        tfUm.setPromptText("Novo Preço");
    }

    public void mudaPosicao1(){
        btComercio.setLayoutX(160);
        btComercio.setLayoutY(180);
    }

    public void mudaPosicao2(){
        btProduto.setLayoutX(160);
        btProduto.setLayoutY(300);
    }

    public void mudaPosicao3(){
        btItem.setLayoutX(160);
        btItem.setLayoutY(420);
    }

    public void mudaPosicao4(){
        btGerenciaItem.setLayoutX(160);
        btGerenciaItem.setLayoutY(540);
    }

    public void posicaoNormal1(){
        btComercio.setLayoutX(125);
        btComercio.setLayoutY(180);
    }

    public void posicaoNormal2(){
        btProduto.setLayoutX(125);
        btProduto.setLayoutY(300);
    }

    public void posicaoNormal3(){
        btItem.setLayoutX(125);
        btItem.setLayoutY(420);
    }

    public void posicaoNormal4(){
        btGerenciaItem.setLayoutX(125);
        btGerenciaItem.setLayoutY(540);
    }

    private void limpaTela(){
        apagaPanes();
        tpInicio.setVisible(false);
        tpFim.setVisible(false);
        tfUm.setVisible(false);
        tfDois.setVisible(false);
        taDescricao.setVisible(false);
        cbCidade.setVisible(false);
        cbComercio.setVisible(false);
        cbProduto.setVisible(false);
        cbDisponivel.setVisible(false);
        cbItem.setVisible(false);
    }

    private void apagaPanes(){
        tfUm.clear();
        tfDois.clear();
        taDescricao.clear();
        tpInicio.setValue(null);
        tpFim.setValue(null);
        cbItem.getSelectionModel().clearSelection();
        cbProduto.getSelectionModel().clearSelection();
        cbCidade.getSelectionModel().clearSelection();
        cbComercio.getSelectionModel().clearSelection();
        Font font = Font.font(32);
        taDescricao.fontProperty().set(font);
        taDescricao.setText("                                       Descrição");
    }

    private void atualizaDados() throws SQLException{
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        produtos.addAll(Controle.getInstance().listaProdutosAdm());
        ObservableList<Comercio> comercios = FXCollections.observableArrayList();
        comercios.addAll(Controle.getInstance().listaComercios());
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(Controle.getInstance().listaItemsAdm());

        cbProduto.setItems(produtos);
        cbComercio.setItems(comercios);
        cbItem.setItems(items);
    }

    public void RealizaCadastro() throws SQLException{
        if(btComercio.isDisable()){
            String nome = tfUm.getText();
            String Endereco = tfDois.getText();
            Time horarioInicio = Time.valueOf(tpInicio.getValue());
            Time horarioFim = Time.valueOf(tpFim.getValue());
            Cidade cidade = cbCidade.getSelectionModel().getSelectedItem();
            Comercio comercio = new Comercio(nome, Endereco, horarioInicio, horarioFim, cidade);
            Controle.getInstance().addComercio(comercio);
            atualizaDados();
            apagaPanes();
        }
        else if(btProduto.isDisable()){
            String nome = tfUm.getText();
            String marca = tfDois.getText();
            String descricao = taDescricao.getText();
            Usuario usuario = Controle.getUsuario();
            Produto produto = new Produto(nome, marca, descricao, usuario.getId());
            Controle.getInstance().addProduto(produto);
            atualizaDados();
            apagaPanes();
        }
        else if(btItem.isDisable()){
            Boolean estoque = cbDisponivel.isSelected();
            Float preco = Float.valueOf(tfUm.getText());
            Produto produto = cbProduto.getSelectionModel().getSelectedItem();
            Comercio comercio = cbComercio.getSelectionModel().getSelectedItem();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            UsuarioAdm usuarioAdm = Controle.getUsuarioAdm();
            Item item = new Item(estoque, preco, produto, comercio, date, usuarioAdm);
            Controle.getInstance().addItem(item);
            atualizaDados();
            apagaPanes();
        }
        else if(btGerenciaItem.isDisable()){
            Item item = cbItem.getSelectionModel().getSelectedItem();
            Float preco = Float.valueOf(tfUm.getText());

            Controle.getInstance().atualizaItem(item, preco);
            apagaPanes();
        }
    }

    public void diminuiLetra(){
        Font font = Font.font(14);
        taDescricao.fontProperty().set(font);
    }
}
