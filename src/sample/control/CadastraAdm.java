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
import sample.model.UsuarioAdm;

import java.io.IOException;
import java.sql.SQLException;

public class CadastraAdm extends Mensagem {

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
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfCpf;
    @FXML
    private Label lbInfo;

    public void initialize() throws SQLException {
        ObservableList<Cidade> cidades = FXCollections.observableArrayList();
        cidades.addAll(Controle.getInstance().carregaCidades());
        cbCidade.setItems(cidades);
    }

    public void proximoPasso() throws Exception {
        if(tfNome.getLength() < 3 || tfEmail.getLength() < 3 || pfSenha.getLength() < 3 || pfConfirmacao.getLength() < 3 || tfTelefone.getLength() < 3 || tfCpf.getLength() < 3){
            Aviso(Alert.AlertType.WARNING, "Preencha todos os dados exigidos!");
            throw new Exception("Dados errados!");
        }
        else {
            if(pfSenha.getText().equals(pfConfirmacao.getText())) {
                String nome = tfNome.getText();
                String email = tfEmail.getText();
                String senha = pfSenha.getText();
                Cidade cidade = cbCidade.getSelectionModel().getSelectedItem();
                String telefone = tfTelefone.getText();
                String cpf = tfCpf.getText();

                if(email.contains("@") && email.contains(".com")) {
                    if (cpf.length() == 14) {
                        if (cpf.substring(3, 4).equals(".") && cpf.substring(7, 8).equals(".") && cpf.substring(11, 12).equals("-")) {
                            Usuario usuario = new Usuario(nome, email, senha, cidade);
                            UsuarioAdm usuarioAdm = new UsuarioAdm(nome, email, senha, cidade, telefone, cpf);

                            Controle.setUsuario(usuario);
                            Controle.setUsuarioAdm(usuarioAdm);
                        } else {
                            Aviso(Alert.AlertType.WARNING, "CPF inválido!");
                        }
                    } else {
                        Aviso(Alert.AlertType.WARNING, "CPF inválido!");
                    }
                }else{
                    Aviso(Alert.AlertType.WARNING, "Email inválido!");
                }
            }
            else
            {
                Aviso(Alert.AlertType.WARNING, "As Senhas estão incorretas!");
                throw new Exception("Dados errados!");
            }
        }
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
