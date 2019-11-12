package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.model.Controle;
import sample.model.Estado;

import java.sql.SQLException;

public class AdcionaCidade extends Mensagem{

    @FXML
    private JFXTextField tfNovaCidade;
    @FXML
    private JFXTextField tfNovoEstado;
    @FXML
    private JFXComboBox<Estado> cbEstado;
    @FXML
    private JFXButton btAddEstado;
    @FXML
    private JFXButton btAddCidade;

    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void initialize() throws SQLException {
        ObservableList<Estado> estados = FXCollections.observableArrayList();
        estados.addAll(Controle.getInstance().listaEstados());
        cbEstado.setItems(estados);
    }



    public void confirmaAdd(ActionEvent event) throws SQLException {
        Estado estado = cbEstado.getSelectionModel().getSelectedItem();
        String nomeCidade = tfNovaCidade.getText();

        if(estado != null && nomeCidade.length() > 3) {
            if (Controle.getInstance().verificaCidade(nomeCidade) == null) {
                Controle.getInstance().addCidade(nomeCidade, estado);
            } else {
                Aviso(Alert.AlertType.WARNING, "Essa cidade já existe");
            }
        }
    }

    public void novoEstado(ActionEvent event) {
        tfNovoEstado.setDisable(false);
        btAddEstado.setDisable(false);
    }

    public void addEstado(ActionEvent event) throws SQLException{
        String nome = tfNovoEstado.getText();

        if(Controle.getInstance().verificaEstado(nome) == null){
            Controle.getInstance().registraNovoEstado(nome);
        }
    }

    public void desabilitaCidade(){
        tfNovaCidade.setDisable(false);
    }

    public void desabilitaBt(){
        if(tfNovaCidade.getLength() > 3){
            btAddCidade.setDisable(false);
        }
    }
}
