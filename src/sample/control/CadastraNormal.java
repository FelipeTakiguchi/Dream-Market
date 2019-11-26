package sample.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Browser;
import sample.model.Cidade;
import sample.model.Controle;
import sample.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

public class CadastraNormal extends Mensagem{

    @FXML
    private JFXButton btFechar;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXPasswordField pfSenha;
    @FXML
    private JFXPasswordField pfConfirmacao;
    @FXML
    private JFXComboBox<Cidade> cbCidade;
    @FXML
    private Label lbInfo;
    @FXML
    private JFXTextField tfEstado;
    @FXML
    private JFXTextField tfNovaCidade;

    public void initialize() throws SQLException {
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        cbCidade.setItems(cidades);
    }

    @FXML
    void acaoFechar(){
        Stage stage = (Stage) btFechar.getScene().getWindow();
        stage.close();
    }

    public void proximoPasso(ActionEvent event) {
        if(tfNome.getLength() < 3 || tfEmail.getLength() < 3 || pfSenha.getLength() < 3 || pfConfirmacao.getLength() < 3 || cbCidade.getSelectionModel().selectedItemProperty().getValue() == null){
            Aviso(Alert.AlertType.WARNING, "Preencha todos os dados exigidos!");
            Controle.setOk(false);
        }
        else {
            if(pfSenha.getText().equals(pfConfirmacao.getText())) {
                String nome = tfNome.getText();
                String email = tfEmail.getText();
                String senha = pfSenha.getText();
                Cidade cidade = cbCidade.getSelectionModel().getSelectedItem();

                Usuario usuario = new Usuario(nome, email, senha, cidade);

                Controle.setUsuario(usuario);
                Controle.setOk(true);
            }
            else
            {
                Aviso(Alert.AlertType.WARNING, "As Senhas estão incorretas!");
                Controle.setOk(false);
            }
        }
    }

    public void voltar(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Browser.CADASTRAUSUARIO));

        try{
            Pane conteudo = loader.load();

            dialog.getDialogPane().setContent(conteudo);

            dialog.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarLabel(MouseEvent event) {
        double xOffset = 0;
        double yOffset = 0;

        lbInfo.setVisible(Boolean.TRUE);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        lbInfo.setLayoutX(xOffset);
        lbInfo.setLayoutY(yOffset);
    }

    public void esconderLabel(MouseEvent event) {
        lbInfo.setVisible(Boolean.FALSE);
    }

    public void novaCidade(ActionEvent event) {
        Browser.loadWindows(Browser.CADASTRACIDADE);
    }
}
