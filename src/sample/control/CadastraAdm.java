package sample.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Browser;

import java.io.IOException;

public class CadastraAdm {
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXPasswordField pfSenha;
    @FXML
    private JFXPasswordField pfConfirmacao;
    @FXML
    private JFXComboBox cbCidade;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfCpf;

    @FXML
    void acaoFechar(){
        System.exit(0);
    }

    public void proximoPasso(ActionEvent event) {
        Browser.loadWindows(Browser.EXIBECONDICOESADM);
    }

    public void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        Browser.BASE
                )
        );

        Base controller = loader.getController();

        Browser.setController(controller);
        Browser.loadWindows(Browser.CADASTRAUSUARIO);

        stage.setScene(new Scene(mainPane, 1280, 720));
        stage.show();
    }
}
